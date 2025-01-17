package api.model.jsonType.response;

import api.model.Amount;
import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public final class SealedInterfaceAmountResponse extends SealedInterfaceSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The amount to sell")
    public Amount amount;

    public SealedInterfaceAmountResponse(Amount amount) {
        super(TransferType.AMOUNT);
        this.amount = amount;
    }
}
