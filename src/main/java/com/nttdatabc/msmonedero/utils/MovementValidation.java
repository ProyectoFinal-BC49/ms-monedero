package com.nttdatabc.msmonedero.utils;

import static com.nttdatabc.msmonedero.utils.Constantes.*;

import com.nttdatabc.msmonedero.model.MovementWallet;
import com.nttdatabc.msmonedero.repository.WalletRepository;
import com.nttdatabc.msmonedero.utils.exceptions.errors.ErrorResponseException;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

/**
 * Validaciones de movimientos.
 */

public class MovementValidation {

  public static Mono<Void> validateCentralFacade(MovementWallet movementWallet, WalletRepository walletRepository) {
    return validateMovementNoNulls(movementWallet)
        .then(validateMovementEmpty(movementWallet))
        .then(verifyValues(movementWallet))
        .then(verifyNumberDestination(movementWallet, walletRepository));
  }

  public static Mono<Void> validateMovementNoNulls(MovementWallet movementWallet) {
    return Mono.just(movementWallet)
        .filter(c -> c.getWalletId() != null)
        .filter(c -> c.getDestinationFor() != null)
        .filter(c -> c.getMount() != null)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_REQUEST,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<Void> validateMovementEmpty(MovementWallet movementWallet) {
    return Mono.just(movementWallet)
        .filter(c -> !c.getWalletId().isEmpty())
        .filter(c -> !c.getDestinationFor().isBlank())
        .filter(c -> !c.getMount().toString().isBlank())
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_VALUE_EMPTY,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<Void> verifyValues(MovementWallet movementWallet) {
    return Mono.just(movementWallet)
        .filter(c -> c.getMount().doubleValue() > VALUE_MIN_ACCOUNT_BANK)
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_VALUE_MIN,
            HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST)))
        .then();
  }

  public static Mono<Void> verifyNumberDestination(MovementWallet movementWallet, WalletRepository walletRepository) {
    return walletRepository.findByNumberPhone(movementWallet.getDestinationFor())
        .hasElement()
        .flatMap(aBoolean -> {
          if (aBoolean) {
            return Mono.empty();
          } else {
            return Mono.error(new ErrorResponseException(EX_ERROR_NOT_FOUND_DESTINATION, HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND));
          }
        });
  }

}
