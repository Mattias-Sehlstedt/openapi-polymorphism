package api.model.jsonType.explicit.type.request;

import api.model.TransferType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = ExplicitTypePercentageRequest.class, names = {"PERCENTAGE", "ExplicitTypePercentageRequest"}),
                @JsonSubTypes.Type(value = ExplicitTypeValueRequest.class, names = {"VALUE", "ExplicitTypeValueRequest"}),
                @JsonSubTypes.Type(value = ExplicitTypeAmountRequest.class, names = {"AMOUNT", "ExplicitTypeAmountRequest"})
        }
)
public abstract sealed class ExplicitTypeSellBitcoinRequest permits ExplicitTypeAmountRequest, ExplicitTypePercentageRequest, ExplicitTypeValueRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private final TransferType type;

    ExplicitTypeSellBitcoinRequest(TransferType type) {
        this.type = type;
    }

    protected String getType() {
        return type.name();
    }
}
