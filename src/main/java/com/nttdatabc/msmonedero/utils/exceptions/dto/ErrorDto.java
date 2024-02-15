package com.nttdatabc.msmonedero.utils.exceptions.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * DTO de Error.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
  private HttpStatus httpStatus;
  private String message;
  private int code;
}
