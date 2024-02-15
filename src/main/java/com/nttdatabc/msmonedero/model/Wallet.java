package com.nttdatabc.msmonedero.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Wallet
 */

@Document(value = "wallet")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-11T11:09:15.941202200-05:00[America/Lima]")
public class Wallet {

  private String id;

  private String typeDocumentIdentification;

  private String numberIdentification;

  private String numberPhone;

  private String imeiPhone;

  private String email;

  private String cardDebitAssociate;

  private BigDecimal balanceTotal;

  public Wallet id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id.
   *
   * @return id.
   */

  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Wallet typeDocumentIdentification(String typeDocumentIdentification) {
    this.typeDocumentIdentification = typeDocumentIdentification;
    return this;
  }

  /**
   * Get typeDocumentIdentification.
   *
   * @return typeDocumentIdentification.
   */

  @Schema(name = "typeDocumentIdentification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("typeDocumentIdentification")
  public String getTypeDocumentIdentification() {
    return typeDocumentIdentification;
  }

  public void setTypeDocumentIdentification(String typeDocumentIdentification) {
    this.typeDocumentIdentification = typeDocumentIdentification;
  }

  public Wallet numberIdentification(String numberIdentification) {
    this.numberIdentification = numberIdentification;
    return this;
  }

  /**
   * Get numberIdentification
   *
   * @return numberIdentification
   */

  @Schema(name = "numberIdentification", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberIdentification")
  public String getNumberIdentification() {
    return numberIdentification;
  }

  public void setNumberIdentification(String numberIdentification) {
    this.numberIdentification = numberIdentification;
  }

  public Wallet numberPhone(String numberPhone) {
    this.numberPhone = numberPhone;
    return this;
  }

  /**
   * Get numberPhone
   *
   * @return numberPhone
   */

  @Schema(name = "numberPhone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberPhone")
  public String getNumberPhone() {
    return numberPhone;
  }

  public void setNumberPhone(String numberPhone) {
    this.numberPhone = numberPhone;
  }

  public Wallet imeiPhone(String imeiPhone) {
    this.imeiPhone = imeiPhone;
    return this;
  }

  /**
   * Get imeiPhone
   *
   * @return imeiPhone
   */

  @Schema(name = "imeiPhone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("imeiPhone")
  public String getImeiPhone() {
    return imeiPhone;
  }

  public void setImeiPhone(String imeiPhone) {
    this.imeiPhone = imeiPhone;
  }

  public Wallet email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   *
   * @return email
   */

  @Schema(name = "email", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Wallet cardDebitAssociate(String cardDebitAssociate) {
    this.cardDebitAssociate = cardDebitAssociate;
    return this;
  }

  /**
   * Get cardDebitAssociate
   *
   * @return cardDebitAssociate
   */

  @Schema(name = "cardDebitAssociate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cardDebitAssociate")
  public String getCardDebitAssociate() {
    return cardDebitAssociate;
  }

  public void setCardDebitAssociate(String cardDebitAssociate) {
    this.cardDebitAssociate = cardDebitAssociate;
  }

  public Wallet balanceTotal(BigDecimal balanceTotal) {
    this.balanceTotal = balanceTotal;
    return this;
  }

  /**
   * Get balanceTotal
   *
   * @return balanceTotal
   */
  @Valid
  @Schema(name = "balanceTotal", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("balanceTotal")
  public BigDecimal getBalanceTotal() {
    return balanceTotal;
  }

  public void setBalanceTotal(BigDecimal balanceTotal) {
    this.balanceTotal = balanceTotal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Wallet wallet = (Wallet) o;
    return Objects.equals(this.id, wallet.id) &&
        Objects.equals(this.typeDocumentIdentification, wallet.typeDocumentIdentification) &&
        Objects.equals(this.numberIdentification, wallet.numberIdentification) &&
        Objects.equals(this.numberPhone, wallet.numberPhone) &&
        Objects.equals(this.imeiPhone, wallet.imeiPhone) &&
        Objects.equals(this.email, wallet.email) &&
        Objects.equals(this.cardDebitAssociate, wallet.cardDebitAssociate) &&
        Objects.equals(this.balanceTotal, wallet.balanceTotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typeDocumentIdentification, numberIdentification, numberPhone, imeiPhone, email, cardDebitAssociate, balanceTotal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Wallet {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    typeDocumentIdentification: ").append(toIndentedString(typeDocumentIdentification)).append("\n");
    sb.append("    numberIdentification: ").append(toIndentedString(numberIdentification)).append("\n");
    sb.append("    numberPhone: ").append(toIndentedString(numberPhone)).append("\n");
    sb.append("    imeiPhone: ").append(toIndentedString(imeiPhone)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    cardDebitAssociate: ").append(toIndentedString(cardDebitAssociate)).append("\n");
    sb.append("    balanceTotal: ").append(toIndentedString(balanceTotal)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
