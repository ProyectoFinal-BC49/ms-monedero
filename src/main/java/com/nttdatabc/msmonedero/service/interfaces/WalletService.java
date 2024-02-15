package com.nttdatabc.msmonedero.service.interfaces;

import com.nttdatabc.msmonedero.model.Wallet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface de wallet service.
 */
public interface WalletService {
  Mono<Void> createWalletService(Wallet wallet);

  Mono<Void> deleteWalletService(String walletId);

  Flux<Wallet> getAllWalletsService();

  Mono<Wallet> getWalletByIdService(String walletId);
}
