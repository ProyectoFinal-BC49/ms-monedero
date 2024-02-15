package com.nttdatabc.msmonedero.repository;

import com.nttdatabc.msmonedero.model.Wallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository de wallet.
 */
@Repository
public interface WalletRepository extends ReactiveMongoRepository<Wallet, String> {
  Mono<Wallet> findByNumberPhone(String numberPhone);
}
