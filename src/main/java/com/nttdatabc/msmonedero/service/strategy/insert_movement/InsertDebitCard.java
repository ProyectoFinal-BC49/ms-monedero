package com.nttdatabc.msmonedero.service.strategy.insert_movement;

import static com.nttdatabc.msmonedero.utils.Constantes.EX_ERROR_BALANCE_INSUFICIENT;
import static com.nttdatabc.msmonedero.utils.Utilitarios.generateUuid;

import com.google.gson.Gson;
import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.MovementWallet;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.MovementWalletRepository;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import com.nttdatabc.msmonedero.utils.exceptions.errors.ErrorResponseException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

/**
 * Estrategia implementada cuando lo asocia a trarjeta de debito.
 */

public class InsertDebitCard extends InsertWhenDebitCard {
  @Override
  Mono<Void> verifyBalanceSuficient(Wallet wallet, MovementWallet movementWallet, KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener) {
    Map<String, String> requestVerifyBalance = new HashMap<>();
    requestVerifyBalance.put("cardDebitId", wallet.getCardDebitAssociate());
    requestVerifyBalance.put("mount", movementWallet.getMount().toString());
    Gson gson = new Gson();
    String requestStr = gson.toJson(requestVerifyBalance);
    kafkaTemplate.send("verify-balance-carddeb", requestStr);

    return kafkaConsumerListener.getDebCardVerificationBalanceResponseSink()
        .flatMap(s -> {
          if (s.contains("error")) {
            return Mono.error(new ErrorResponseException(EX_ERROR_BALANCE_INSUFICIENT,
                HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT));
          } else {
            return Mono.empty();
          }
        });
  }

  @Override
  Mono<Void> insertMovement(MovementWallet movementWallet, MovementWalletRepository movementWalletRepository) {
    movementWallet.setId(generateUuid());
    return movementWalletRepository.save(movementWallet)
        .then();
  }

  @Override
  Mono<Void> updateWalletOrigin(Wallet wallet, MovementWallet movementWallet, KafkaTemplate<String, String> kafkaTemplate) {
    return Mono.just(wallet)
        .flatMap(walletFlujo -> {
          Map<String, String> requestUpdateMoney = new HashMap<>();
          requestUpdateMoney.put("cardDebitId", walletFlujo.getCardDebitAssociate());
          requestUpdateMoney.put("mount", movementWallet.getMount().toString());
          requestUpdateMoney.put("type", "decrease");
          Gson gson = new Gson();
          String requestStr = gson.toJson(requestUpdateMoney);
          kafkaTemplate.send("update-amount-carddeb", requestStr);
          return Mono.empty();
        });
  }

  @Override
  Mono<Void> updateWalletDestin(Wallet wallet, MovementWallet movementWallet, WalletRepository walletRepository, KafkaTemplate<String, String> kafkaTemplate) {
    return Mono.just(wallet)
        .then(walletRepository.findByNumberPhone(movementWallet.getDestinationFor()))
        .flatMap(walletFlujo -> {
          if (walletFlujo.getCardDebitAssociate() == null) {
            walletFlujo.setBalanceTotal(walletFlujo.getBalanceTotal().add(movementWallet.getMount()));
            return walletRepository.save(walletFlujo);
          } else {
            Map<String, String> requestUpdateMoney = new HashMap<>();
            requestUpdateMoney.put("cardDebitId", walletFlujo.getCardDebitAssociate());
            requestUpdateMoney.put("mount", movementWallet.getMount().toString());
            requestUpdateMoney.put("type", "increase");
            Gson gson = new Gson();
            String requestStr = gson.toJson(requestUpdateMoney);
            kafkaTemplate.send("update-amount-carddeb", requestStr);
            return Mono.empty();
          }
        }).then();
  }
}
