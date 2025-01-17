package api.model.discriminator.request;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class SealedClassSellValueRequest extends SealedClassSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The value to sell", example = "1.1")
    @NotNull
    public BigDecimal value;

    public SealedClassSellValueRequest(BigDecimal value) {
        super(TransferType.VALUE);
        this.value = value;
    }
}
