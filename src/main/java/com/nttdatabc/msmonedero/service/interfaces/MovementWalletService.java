package com.nttdatabc.msmonedero.service.interfaces;

import com.nttdatabc.msmonedero.model.MovementWallet;
import reactor.core.publisher.Mono;

public interface MovementWalletService {
  Mono<Void> createMovementWalletservice(MovementWallet wallet);
}
