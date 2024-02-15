package com.nttdatabc.msmonedero.controller;

import static com.nttdatabc.msmonedero.utils.Constantes.PREFIX_PATH;

import com.nttdatabc.msmonedero.controller.interfaces.WalletControllerApi;
import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.service.WalletServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Controller Wallet.
 */
@RestController
@Slf4j
@RequestMapping(PREFIX_PATH)
public class WalletController implements WalletControllerApi {
  @Autowired
  private WalletServiceImpl walletService;

  @Override
  public ResponseEntity<Mono<Void>> createWallet(Wallet wallet, ServerWebExchange exchange) {
    return new ResponseEntity<>(walletService.createWalletService(wallet)
        .doOnSubscribe(unused -> log.info("createWallet:: iniciando"))
        .doOnError(throwable -> log.error("createWallet:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("createWallet:: finalizado con exito")), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Mono<Void>> deleteWallet(String walletId, ServerWebExchange exchange) {
    return new ResponseEntity<>(walletService.deleteWalletService(walletId)
        .doOnSubscribe(unused -> log.info("deleteWallet:: iniciando"))
        .doOnError(throwable -> log.error("deleteWallet:: error " + throwable.getMessage()))
        .doOnSuccess(ignored -> log.info("deleteWallet:: finalizado con exito")), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Flux<Wallet>> getAllWallets(ServerWebExchange exchange) {
    return ResponseEntity.ok(
        walletService.getAllWalletsService()
            .doOnSubscribe(unused -> log.info("getAllWallets:: iniciando"))
            .doOnError(throwable -> log.error("getAllWallets:: error " + throwable.getMessage()))
            .doOnComplete(() -> log.info("getAllWallets:: completadoo"))
    );
  }

  @Override
  public ResponseEntity<Mono<Wallet>> getWalletById(String walletId, ServerWebExchange exchange) {
    return ResponseEntity.ok(
        walletService.getWalletByIdService(walletId)
            .doOnSubscribe(unused -> log.info("getWalletById:: iniciando"))
            .doOnError(throwable -> log.error("getWalletById:: error " + throwable.getMessage()))
            .doOnSuccess((e) -> log.info("getWalletById:: completadoo")));
  }
}
