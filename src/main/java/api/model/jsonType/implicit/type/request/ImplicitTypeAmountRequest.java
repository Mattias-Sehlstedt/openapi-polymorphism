package api.model.jsonType.implicit.type.request;

import api.model.Amount;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public final class ImplicitTypeAmountRequest implements ImplicitTypeSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The amount to sell")
    @NotNull
    public Amount amount;

    @JsonCreator
    public ImplicitTypeAmountRequest(Amount amount) {
        this.amount = amount;
    }
}
