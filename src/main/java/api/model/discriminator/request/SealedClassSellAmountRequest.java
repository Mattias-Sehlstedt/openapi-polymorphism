package api.model.discriminator.request;

import api.model.Amount;
import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public final class SealedClassSellAmountRequest extends SealedClassSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The amount to sell")
    @NotNull
    public Amount amount;

    public SealedClassSellAmountRequest(Amount amount) {
        super(TransferType.AMOUNT);
        this.amount = amount;
    }
}
