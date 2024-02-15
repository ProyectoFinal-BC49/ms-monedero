package com.nttdatabc.msmonedero.service;


import static com.nttdatabc.msmonedero.utils.Constantes.EX_NOT_FOUND_RECURSO;
import static com.nttdatabc.msmonedero.utils.Constantes.VALUE_INIT_CREATE_WALLET;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import com.nttdatabc.msmonedero.service.interfaces.WalletService;
import com.nttdatabc.msmonedero.service.strategy.validation_wallet.ContextValidation;
import com.nttdatabc.msmonedero.service.strategy.validation_wallet.ValidateDebitCard;
import com.nttdatabc.msmonedero.service.strategy.validation_wallet.ValidateDocument;
import com.nttdatabc.msmonedero.utils.Utilitarios;
import com.nttdatabc.msmonedero.utils.exceptions.errors.ErrorResponseException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Service de wallet.
 */
@Service
public class WalletServiceImpl implements WalletService {

  @Autowired
  private WalletRepository walletRepository;
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  @Autowired
  private KafkaConsumerListener kafkaConsumerListener;
  @Autowired
  @Qualifier("walletReactiveRedisTemplate")
  private ReactiveRedisTemplate<String, Wallet> redisTemplate;

  @Override
  public Mono<Void> createWalletService(Wallet wallet) {
    ContextValidation ctxValidation = null;
    if (!Objects.isNull(wallet.getCardDebitAssociate())) {
      ctxValidation = new ContextValidation(new ValidateDebitCard());
    } else {
      ctxValidation = new ContextValidation(new ValidateDocument());
    }
    return ctxValidation.executeValidation(wallet, walletRepository, kafkaTemplate, kafkaConsumerListener)
        .then(Mono.just(wallet))
        .flatMap(walletMono -> {
          walletMono.setId(Utilitarios.generateUuid());
          walletMono.setBalanceTotal(BigDecimal.valueOf(VALUE_INIT_CREATE_WALLET));
          return walletRepository.save(walletMono);
        }).then();

  }

  @Override
  public Mono<Void> deleteWalletService(String walletId) {
    return getWalletByIdService(walletId)
        .flatMap(wallet -> walletRepository.delete(wallet))
        .then();
  }

  @Override
  public Flux<Wallet> getAllWalletsService() {
    String cacheKey = "wallets";
    Duration cacheDuration = Duration.ofMinutes(5);
    return redisTemplate.opsForList().range(cacheKey, 0, -1)
        .switchIfEmpty(walletRepository.findAll()
            .flatMap(wallet -> redisTemplate.opsForList().leftPushAll(cacheKey, wallet)
                .thenMany(Flux.just(wallet))))
        .cache(cacheDuration)
        .doOnSubscribe(subscription -> redisTemplate.expire(cacheKey, cacheDuration).subscribe());
  }

  @Override
  public Mono<Wallet> getWalletByIdService(String walletId) {
    return walletRepository.findById(walletId)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }
  private Mono<Wallet> getWalletByNumberPhone(String numberPhone) {
    return walletRepository.findByNumberPhone(numberPhone)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_NOT_FOUND_RECURSO,
            HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND)));
  }

  @KafkaListener(topics = {"verify-modepayment-yanki-bootcoin"}, groupId = "my-group-id")
  private void verifyModePaymentYankiBootcoin(String message){
    Gson gson = new Gson();
    Map<String, String> map = gson.fromJson(message, new TypeToken<Map<String, String>>() {
    }.getType());
    String idUserBuy = map.get("idUserBuy");
    String idUserSeller = map.get("idUserSeller");
    String modeCharged = map.get("modeCharged");
    double mountSolesCalculated = Double.parseDouble(map.get("mountSolesCalculated"));

    getWalletByNumberPhone(idUserBuy).subscribe(wallet -> {
      if(mountSolesCalculated > wallet.getBalanceTotal().doubleValue()){
        map.put("reasonRejection", "No cuenta con saldo suficiente");
        String responseErrorGson = gson.toJson(map);
        kafkaTemplate.send("validation-transaction-error", responseErrorGson);
      }else{
        if(modeCharged.equalsIgnoreCase("yanki")){
          getWalletByNumberPhone(idUserSeller).subscribe(walletCharged -> {
            kafkaTemplate.send("validation-transaction-success", message);
          },throwable -> {
            map.put("reasonRejection", "No existe el número de celular de Vendedor.");
            String responseErrorGson = gson.toJson(map);
            kafkaTemplate.send("validation-transaction-error", responseErrorGson);
          });
        }

      }
    },throwable -> {
      map.put("reasonRejection", "No existe el número de celular en wallet.");
      String responseErrorGson = gson.toJson(map);
      kafkaTemplate.send("validation-transaction-error", responseErrorGson);
    });
  }

  @KafkaListener(topics = {"effect-transaction"}, groupId = "my-group-id")
  private void listenerEffectTransaction(String message){
    Gson gson = new Gson();
    Map<String, String> map = gson.fromJson(message, new TypeToken<Map<String, String>>() {
    }.getType());
    String idUserBuy = map.get("idUserBuy");
    String idUserSeller = map.get("idUserSeller");
    double mountSolesCalculated = Double.parseDouble(map.get("mountSolesCalculated"));

    getWalletByNumberPhone(idUserBuy).flatMap(wallet -> {
      wallet.setBalanceTotal(wallet.getBalanceTotal().subtract(BigDecimal.valueOf(mountSolesCalculated)));
      return walletRepository.save(wallet);
    }).subscribe();
    getWalletByNumberPhone(idUserSeller).flatMap(wallet -> {
      wallet.setBalanceTotal(wallet.getBalanceTotal().add(BigDecimal.valueOf(mountSolesCalculated)));
      return walletRepository.save(wallet);
    }).subscribe();
  }
}
