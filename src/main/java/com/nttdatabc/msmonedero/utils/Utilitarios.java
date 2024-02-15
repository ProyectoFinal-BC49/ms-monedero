package com.nttdatabc.msmonedero.utils;

import java.util.UUID;

/**
 * Clase utilitarios.
 */
public class Utilitarios {
  public static String generateUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
