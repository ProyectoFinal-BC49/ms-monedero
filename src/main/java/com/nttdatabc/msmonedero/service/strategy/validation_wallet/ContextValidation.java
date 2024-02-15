package com.nttdatabc.msmonedero.service.strategy.validation_wallet;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

/**
 * Context de validaciones.
 */
public class ContextValidation {
  private IStrategyValidateWallet iStrategyValidateWallet;

  public ContextValidation(IStrategyValidateWallet iStrategyValidateWallet) {
    this.iStrategyValidateWallet = iStrategyValidateWallet;
  }

  public Mono<Void> executeValidation(Wallet wallet, WalletRepository walletRepository, KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener) {
    return iStrategyValidateWallet.validate(wallet, walletRepository, kafkaTemplate, kafkaConsumerListener);
  }
}
