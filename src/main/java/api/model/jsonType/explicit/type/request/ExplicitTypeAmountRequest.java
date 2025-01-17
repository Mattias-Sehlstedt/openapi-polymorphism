package api.model.jsonType.explicit.type.request;

import api.model.Amount;
import api.model.TransferType;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ExplicitTypeAmountRequest extends ExplicitTypeSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The amount to sell")
    @NotNull
    public Amount amount;

    @JsonCreator
    public ExplicitTypeAmountRequest(Amount amount) {
        super(TransferType.AMOUNT);
        this.amount = amount;
    }
}
