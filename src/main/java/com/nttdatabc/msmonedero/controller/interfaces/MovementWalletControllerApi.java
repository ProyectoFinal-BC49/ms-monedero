package com.nttdatabc.msmonedero.controller.interfaces;

import com.nttdatabc.msmonedero.model.MovementWallet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-11T11:09:15.941202200-05:00[America/Lima]")
@Validated
@Tag(name = "Movement Wallet", description = "the Movement Wallet API")
public interface MovementWalletControllerApi {

  /**
   * POST /movement_wallet/send : crear movimiento del wallet, enviar dinero.
   *
   * @param movementWallet  (optional)
   * @return Envío exitoso (status code 201)
   *         or Error en request (status code 400)
   *         or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "createMovementWallet",
      summary = "crear movimiento del wallet, enviar dinero.",
      tags = { "Movement Wallet" },
      responses = {
          @ApiResponse(responseCode = "201", description = "Envío exitoso"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/movement_wallet/send",
      consumes = { "application/json" }
  )
  default ResponseEntity<Mono<Void>> createMovementWallet(
      @Parameter(name = "MovementWallet", description = "") @Valid @RequestBody(required = false) MovementWallet movementWallet,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }

}

