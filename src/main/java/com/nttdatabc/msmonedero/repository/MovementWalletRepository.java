package com.nttdatabc.msmonedero.repository;

import com.nttdatabc.msmonedero.model.MovementWallet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementWalletRepository extends ReactiveMongoRepository<MovementWallet, String> {
}
