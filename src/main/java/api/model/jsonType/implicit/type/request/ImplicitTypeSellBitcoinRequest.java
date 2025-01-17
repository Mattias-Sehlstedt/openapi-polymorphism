package api.model.jsonType.implicit.type.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = ImplicitTypePercentageRequest.class, name = "PERCENTAGE"),
                @JsonSubTypes.Type(value = ImplicitTypeValueRequest.class, name = "VALUE"),
                @JsonSubTypes.Type(value = ImplicitTypeAmountRequest.class, name = "AMOUNT")
        }
)
public sealed interface ImplicitTypeSellBitcoinRequest permits ImplicitTypeAmountRequest, ImplicitTypePercentageRequest, ImplicitTypeValueRequest {

}
