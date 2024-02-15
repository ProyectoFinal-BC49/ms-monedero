package com.nttdatabc.msmonedero.service.strategy.validation_wallet;

import com.nttdatabc.msmonedero.config.KafkaConsumerListener;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;

public interface IStrategyValidateWallet {
   Mono<Void> validate(Wallet wallet, WalletRepository walletRepository,
                       KafkaTemplate<String, String> kafkaTemplate, KafkaConsumerListener kafkaConsumerListener);
}
