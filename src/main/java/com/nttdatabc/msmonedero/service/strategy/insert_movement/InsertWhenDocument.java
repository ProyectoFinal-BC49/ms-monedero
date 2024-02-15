package com.nttdatabc.msmonedero.service.strategy.insert_movement;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.MovementWallet;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.MovementWalletRepository;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

public abstract class InsertWhenDocument implements IStrategyInsertMovement{
  @Override
  public Mono<Void> insert(Wallet wallet, MovementWallet movementWallet,
                           MovementWalletRepository movementWalletRepository,
                           WalletRepository walletRepository,
                           KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener) {
    return verifyBalanceSuficien(wallet, movementWallet)
        .then(insertMovement(movementWallet, movementWalletRepository))
        .then(updateWalletOrigin(wallet, movementWallet, walletRepository))
        .then(updateWalletDestin(wallet, movementWallet, walletRepository, kafkaTemplate));
  }

  abstract Mono<Void>verifyBalanceSuficien(Wallet wallet, MovementWallet movementWallet);
  abstract Mono<Void>insertMovement(MovementWallet movementWallet, MovementWalletRepository movementWalletRepository);
  abstract Mono<Void>updateWalletOrigin(Wallet wallet, MovementWallet movementWallet, WalletRepository walletRepository);
  abstract Mono<Void>updateWalletDestin(Wallet wallet, MovementWallet movementWallet,
                                        WalletRepository walletRepository, KafkaTemplate<String, String> kafkaTemplate);
}
