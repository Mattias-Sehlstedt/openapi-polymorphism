/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package polymorphism.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * An amount
 */
@JsonPropertyOrder({
  AmountDTO.JSON_PROPERTY_VALUE,
  AmountDTO.JSON_PROPERTY_CURRENCY
})
@JsonTypeName("Amount")
@lombok.experimental.SuperBuilder
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-06-14T11:19:01.680047700+02:00[Europe/Stockholm]", comments = "Generator version: 7.13.0")
public class AmountDTO {
  public static final String JSON_PROPERTY_VALUE = "value";
  @jakarta.annotation.Nonnull
  private BigDecimal value;

  public static final String JSON_PROPERTY_CURRENCY = "currency";
  @jakarta.annotation.Nonnull
  private String currency;

  public AmountDTO() {
  }

  public AmountDTO value(@jakarta.annotation.Nonnull BigDecimal value) {
    
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public BigDecimal getValue() {
    return value;
  }


  @JsonProperty(JSON_PROPERTY_VALUE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setValue(@jakarta.annotation.Nonnull BigDecimal value) {
    this.value = value;
  }

  public AmountDTO currency(@jakarta.annotation.Nonnull String currency) {
    
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
   */
  @jakarta.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_CURRENCY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)

  public String getCurrency() {
    return currency;
  }


  @JsonProperty(JSON_PROPERTY_CURRENCY)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setCurrency(@jakarta.annotation.Nonnull String currency) {
    this.currency = currency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AmountDTO amount = (AmountDTO) o;
    return Objects.equals(this.value, amount.value) &&
        Objects.equals(this.currency, amount.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AmountDTO {\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
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

