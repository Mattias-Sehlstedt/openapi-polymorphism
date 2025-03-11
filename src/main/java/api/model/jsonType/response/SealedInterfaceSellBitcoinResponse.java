package api.model.jsonType.response;

import api.model.TransferType;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = SealedInterfacePercentageResponse.class, names = {"PERCENTAGE", "SealedInterfacePercentageResponse"}),
                @JsonSubTypes.Type(value = SealedInterfaceValueResponse.class, names = {"VALUE", "SealedInterfaceValueResponse"}),
                @JsonSubTypes.Type(value = SealedInterfaceAmountResponse.class, names = {"AMOUNT", "SealedInterfaceAmountResponse"})
        }
)
public abstract sealed class SealedInterfaceSellBitcoinResponse permits SealedInterfaceAmountResponse, SealedInterfacePercentageResponse, SealedInterfaceValueResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private final TransferType type;

    protected SealedInterfaceSellBitcoinResponse(TransferType type) {
        this.type = type;
    }

    public String transferType() {
        return type.name();
    }
}
