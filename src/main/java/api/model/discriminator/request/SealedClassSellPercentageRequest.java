package api.model.discriminator.request;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class SealedClassSellPercentageRequest extends SealedClassSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The percentage to sell", example = "100")
    @NotNull
    public BigDecimal percentage;

    public SealedClassSellPercentageRequest(BigDecimal percentage) {
        super(TransferType.PERCENTAGE);
        this.percentage = percentage;
    }
}
