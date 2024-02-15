package com.nttdatabc.msmonedero.service.strategy.insert_movement;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.MovementWallet;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.MovementWalletRepository;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

/**
 * Clase abstracta de la estrategia.
 */
public abstract class InsertWhenDebitCard implements IStrategyInsertMovement {
  @Override
  public Mono<Void> insert(Wallet wallet, MovementWallet movementWallet, MovementWalletRepository movementWalletRepository,
                           WalletRepository walletRepository, KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener) {
    return verifyBalanceSuficient(wallet, movementWallet, kafkaTemplate, kafkaConsumerListener)
        .then(insertMovement(movementWallet, movementWalletRepository))
        .then(updateWalletOrigin(wallet, movementWallet, kafkaTemplate))
        .then(updateWalletDestin(wallet, movementWallet, walletRepository, kafkaTemplate));
  }

  abstract Mono<Void> verifyBalanceSuficient(Wallet wallet, MovementWallet movementWallet, KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener);

  abstract Mono<Void> insertMovement(MovementWallet movementWallet, MovementWalletRepository movementWalletRepository);

  abstract Mono<Void> updateWalletOrigin(Wallet wallet, MovementWallet movementWallet, KafkaTemplate<String, String> kafkaTemplate);

  abstract Mono<Void> updateWalletDestin(Wallet wallet, MovementWallet movementWallet,
                                         WalletRepository walletRepository, KafkaTemplate<String, String> kafkaTemplate);
}
