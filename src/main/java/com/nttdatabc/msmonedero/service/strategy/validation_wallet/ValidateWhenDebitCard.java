package com.nttdatabc.msmonedero.service.strategy.validation_wallet;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

public abstract class ValidateWhenDebitCard implements IStrategyValidateWallet {
  @Override
  public Mono<Void> validate(Wallet wallet, WalletRepository walletRepository, KafkaTemplate<String, String> kafkaTemplate,KafkaConsumerListener kafkaConsumerListener) {
    return validateWalletNoNulls(wallet)
        .then(validateWalletEmpty(wallet))
        .then(verifyTypeAccount(wallet))
        .then(verifyDataDuplicated(wallet, walletRepository))
        .then(verifyExistCardDeb(wallet, kafkaTemplate, kafkaConsumerListener));
  }

  public abstract Mono<Void> validateWalletNoNulls(Wallet wallet);
  public abstract Mono<Void> validateWalletEmpty(Wallet wallet);
  public abstract Mono<Void> verifyTypeAccount(Wallet wallet);
  public abstract Mono<Void> verifyDataDuplicated(Wallet wallet, WalletRepository walletRepository);
  public abstract Mono<Void> verifyExistCardDeb(Wallet wallet, KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener);
}
