package api.model.discriminator.response;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class SealedClassPercentageResponse extends SealedClassSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The percentage to sell", example = "100")
    public BigDecimal percentage;

    public SealedClassPercentageResponse(BigDecimal percentage) {
        super(TransferType.PERCENTAGE);
        this.percentage = percentage;
    }
}
