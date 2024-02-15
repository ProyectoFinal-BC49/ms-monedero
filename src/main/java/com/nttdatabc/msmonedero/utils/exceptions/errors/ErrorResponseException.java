package com.nttdatabc.msmonedero.utils.exceptions.errors;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Clase de excepción personalizada para manejar respuestas de error con información detallada.
 * Extiende la clase Exception y utiliza la anotación @Data de Lombok para generar automáticamente
 * los métodos getter, setter, toString, entre otros.
 */
@Data
public class ErrorResponseException extends Exception {
  HttpStatus httpStatus;
  int status;

  /**
   * Constructor de la excepción.
   *
   * @param messaage   El mensaje de error asociado a la excepción.
   * @param status     El código personalizado asociado a la excepción.
   * @param httpStatus El código de estado HTTP de la excepción.
   */
  public ErrorResponseException(String messaage, int status, HttpStatus httpStatus) {
    super(messaage);
    this.httpStatus = httpStatus;
    this.status = status;
  }
}