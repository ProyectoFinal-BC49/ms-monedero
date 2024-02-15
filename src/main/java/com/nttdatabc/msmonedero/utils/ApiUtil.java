package com.nttdatabc.msmonedero.utils;

import java.nio.charset.StandardCharsets;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Utilitario para el la interfaz de controller, esto me provee el generator de swagger.
 */
public class ApiUtil {
  /**
   * Ejemplo de una respuesta, esto lo provee el swagger generator.
   *
   * @param exchange swagger.
   * @param mediaType swagger.
   * @param example swagger.
   * @return un mono void.
   */
  public static Mono<Void> getExampleResponse(ServerWebExchange exchange, MediaType mediaType, String example) {
    ServerHttpResponse response = exchange.getResponse();
    response.getHeaders().setContentType(mediaType);

    byte[] exampleBytes = example.getBytes(StandardCharsets.UTF_8);
    DefaultDataBuffer data = new DefaultDataBufferFactory().wrap(exampleBytes);
    return response.writeWith(Mono.just(data));
  }
}
