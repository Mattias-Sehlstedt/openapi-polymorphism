package api.model.jsonType.response;

import api.model.Amount;
import api.model.TransferType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@JsonTypeName("AMOUNT")
public final class SealedInterfaceAmountResponse extends SealedInterfaceSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The amount to sell")
    public Amount amount;

    @JsonCreator
    public SealedInterfaceAmountResponse(@JsonProperty("amount") Amount amount) {
        super(TransferType.AMOUNT);
        this.amount = amount;
    }
}
