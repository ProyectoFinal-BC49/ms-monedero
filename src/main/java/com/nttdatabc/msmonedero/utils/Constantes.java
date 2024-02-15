package com.nttdatabc.msmonedero.utils;

/**
 * Esta clase contiene las constantes de toda la aplicación.
 */
public class Constantes {
  public static final String PREFIX_PATH = "/api/v1";
  public static final String EX_ERROR_REQUEST = "Error en uno de los parámetros";
  public static final String EX_ERROR_TYPE_PERSONA = "Solo existe tipo PERSONA|EMPRESA";
  public static final String EX_VALUE_EMPTY = "Uno de los parámetros viene vacío";
  public static final String EX_NOT_FOUND_RECURSO = "No existe el recurso";
  public static final String EX_USER_REGISTRED = "Este documento ya ha sido registrado.";
  public static final String EX_NOT_FOUND_RECURSO_CARD_DEB = "El id de la tarjeta no existe.";
  public static final String EX_ERROR_TYPE_DOCUMENT = "Recuerda que solo existe tipo DNI | CEX | PASAPORTE";
  public static final double VALUE_INIT_CREATE_WALLET = 0.0;
  public static final String EX_ERROR_PHONE_NUMBER_DUPLICATE = "El número de celular ya ha sido registrado anteriormente";
  public static final double VALUE_MIN_ACCOUNT_BANK = 0.0;
  public static final String EX_ERROR_VALUE_MIN = "Ingrese un valor válido";
  public static final String EX_ERROR_NOT_FOUND_DESTINATION = "El número de celular no está asociado a una wallet.";
  public static final String EX_ERROR_BALANCE_INSUFICIENT = "No tiene saldo suficiente en su cuenta";
}

