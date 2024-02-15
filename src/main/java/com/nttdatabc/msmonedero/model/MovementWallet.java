package com.nttdatabc.msmonedero.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Generated;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MovementWallet.
 */
@Document(value = "movementWallet")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-02-11T11:09:15.941202200-05:00[America/Lima]")
public class MovementWallet {

  private String id;

  private String walletId;

  private String destinationFor;

  private String dateMovement;

  private BigDecimal mount;

  public MovementWallet id(String id) {
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

  public MovementWallet walletId(String walletId) {
    this.walletId = walletId;
    return this;
  }

  /**
   * Get walletId.
   *
   * @return walletId.
   */

  @Schema(name = "walletId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("walletId")
  public String getWalletId() {
    return walletId;
  }

  public void setWalletId(String walletId) {
    this.walletId = walletId;
  }


  public MovementWallet destinationFor(String destinationFor) {
    this.destinationFor = destinationFor;
    return this;
  }

  /**
   * Get destinationFor.
   *
   * @return destinationFor.
   */

  @Schema(name = "destinationFor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("destinationFor")
  public String getDestinationFor() {
    return destinationFor;
  }

  public void setDestinationFor(String destinationFor) {
    this.destinationFor = destinationFor;
  }

  public MovementWallet dateMovement(String dateMovement) {
    this.dateMovement = dateMovement;
    return this;
  }

  /**.
   * Get dateMovement.
   *
   * @return dateMovement.
   */

  @Schema(name = "dateMovement", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dateMovement")
  public String getDateMovement() {
    return dateMovement;
  }

  public void setDateMovement(String dateMovement) {
    this.dateMovement = dateMovement;
  }

  public MovementWallet mount(BigDecimal mount) {
    this.mount = mount;
    return this;
  }

  /**
   * Get mount.
   *
   * @return mount.
   */
  @Valid
  @Schema(name = "mount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mount")
  public BigDecimal getMount() {
    return mount;
  }

  public void setMount(BigDecimal mount) {
    this.mount = mount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovementWallet movementWallet = (MovementWallet) o;
    return Objects.equals(this.id, movementWallet.id)
        && Objects.equals(this.walletId, movementWallet.walletId)
        && Objects.equals(this.destinationFor, movementWallet.destinationFor)
        && Objects.equals(this.dateMovement, movementWallet.dateMovement)
        && Objects.equals(this.mount, movementWallet.mount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, walletId, destinationFor, dateMovement, mount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MovementWallet {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    walletId: ").append(toIndentedString(walletId)).append("\n");
    sb.append("    destinationFor: ").append(toIndentedString(destinationFor)).append("\n");
    sb.append("    dateMovement: ").append(toIndentedString(dateMovement)).append("\n");
    sb.append("    mount: ").append(toIndentedString(mount)).append("\n");
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

