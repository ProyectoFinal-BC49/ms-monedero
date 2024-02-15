package com.nttdatabc.msmonedero.controller;

import static com.nttdatabc.msmonedero.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.msmonedero.controller.interfaces.MovementWalletControllerApi;
import com.nttdatabc.msmonedero.model.MovementWallet;
import com.nttdatabc.msmonedero.service.MovementWalletServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


/**
 * Controller movementwallet.
 */
@RestController
@Slf4j
@RequestMapping(PREFIX_PATH)
public class MovementWalletControler implements MovementWalletControllerApi {
  @Autowired
  private MovementWalletServiceImpl movementWalletService;

  @Override
  public ResponseEntity<Mono<Void>> createMovementWallet(MovementWallet movementWallet, ServerWebExchange exchange) {
    return new ResponseEntity<>(movementWalletService.createMovementWalletservice(movementWallet)
        .doOnSubscribe(unused -> log.info("createMovementWallet:: iniciando"))
        .doOnError(throwable -> log.error("createMovementWallet:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createMovementWallet:: finalizado con exito")),
        HttpStatus.CREATED);
  }
}
