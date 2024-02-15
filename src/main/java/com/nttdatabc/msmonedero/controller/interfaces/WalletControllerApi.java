package com.nttdatabc.msmonedero.controller.interfaces;

import com.nttdatabc.msmonedero.model.Wallet;
import com.nttdatabc.msmonedero.utils.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-11T11:09:15.941202200-05:00[America/Lima]")
@Validated
@Tag(name = "Wallet", description = "the Wallet API")
public interface WalletControllerApi {

  /**
   * POST /wallet : Agregar Wallet
   *
   * @param wallet (optional)
   * @return Cuenta bancaria creada exitosamente (status code 201)
   * or Error en Request (status code 400)
   */
  @Operation(
      operationId = "createWallet",
      summary = "Agregar Wallet",
      tags = {"Wallet"},
      responses = {
          @ApiResponse(responseCode = "201", description = "Cuenta bancaria creada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en Request")
      }
  )
  @RequestMapping(
      method = RequestMethod.POST,
      value = "/wallet",
      consumes = {"application/json"}
  )
  default ResponseEntity<Mono<Void>> createWallet(
      @Parameter(name = "Wallet", description = "") @Valid @RequestBody(required = false) Wallet wallet,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * DELETE /wallet/{wallet_id} : Eliminar Wallet
   *
   * @param walletId ID del wallet (required)
   * @return Cuenta bancaria eliminada exitosamente (status code 200)
   * or Error en request (status code 400)
   * or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "deleteWallet",
      summary = "Eliminar Wallet",
      tags = {"Wallet"},
      responses = {
          @ApiResponse(responseCode = "200", description = "Cuenta bancaria eliminada exitosamente"),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.DELETE,
      value = "/wallet/{wallet_id}"
  )
  default ResponseEntity<Mono<Void>> deleteWallet(
      @Parameter(name = "wallet_id", description = "ID del wallet", required = true, in = ParameterIn.PATH) @PathVariable("wallet_id") String walletId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    return ResponseEntity.ok().build();

  }


  /**
   * GET /wallet : Obtener todos los wallets
   *
   * @return Lista de wallets obtenida correctamente (status code 200)
   */
  @Operation(
      operationId = "getAllWallets",
      summary = "Obtener todos los wallets",
      tags = {"Wallet"},
      responses = {
          @ApiResponse(responseCode = "200", description = "Lista de wallets obtenida correctamente", content = {
              @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Wallet.class)))
          })
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/wallet",
      produces = {"application/json"}
  )
  default ResponseEntity<Flux<Wallet>> getAllWallets(
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "[ { \"balanceTotal\" : 0.8008281904610115, \"numberIdentification\" : \"numberIdentification\", \"numberPhone\" : \"numberPhone\", \"cardDebitAssociate\" : \"cardDebitAssociate\", \"typeDocumentIdentification\" : \"typeDocumentIdentification\", \"imeiPhone\" : \"imeiPhone\", \"id\" : \"id\", \"email\" : \"email\" }, { \"balanceTotal\" : 0.8008281904610115, \"numberIdentification\" : \"numberIdentification\", \"numberPhone\" : \"numberPhone\", \"cardDebitAssociate\" : \"cardDebitAssociate\", \"typeDocumentIdentification\" : \"typeDocumentIdentification\", \"imeiPhone\" : \"imeiPhone\", \"id\" : \"id\", \"email\" : \"email\" } ]";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();

  }


  /**
   * GET /wallet/{wallet_id} : Obtener detalle de Wallet
   *
   * @param walletId ID del wallet (required)
   * @return Detalle de Wallet obtenido exitosamente (status code 200)
   * or Error en request (status code 400)
   * or Recurso no encontrado (status code 404)
   */
  @Operation(
      operationId = "getWalletById",
      summary = "Obtener detalle de Wallet",
      tags = {"Wallet"},
      responses = {
          @ApiResponse(responseCode = "200", description = "Detalle de Wallet obtenido exitosamente", content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = Wallet.class))
          }),
          @ApiResponse(responseCode = "400", description = "Error en request"),
          @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
      }
  )
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/wallet/{wallet_id}",
      produces = {"application/json"}
  )
  default ResponseEntity<Mono<Wallet>> getWalletById(
      @Parameter(name = "wallet_id", description = "ID del wallet", required = true, in = ParameterIn.PATH) @PathVariable("wallet_id") String walletId,
      @Parameter(hidden = true) final ServerWebExchange exchange
  ) {
    Mono<Void> result = Mono.empty();
    exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
    for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
      if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
        String exampleString = "{ \"balanceTotal\" : 0.8008281904610115, \"numberIdentification\" : \"numberIdentification\", \"numberPhone\" : \"numberPhone\", \"cardDebitAssociate\" : \"cardDebitAssociate\", \"typeDocumentIdentification\" : \"typeDocumentIdentification\", \"imeiPhone\" : \"imeiPhone\", \"id\" : \"id\", \"email\" : \"email\" }";
        result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
        break;
      }
    }
    return ResponseEntity.ok().build();
  }

}
