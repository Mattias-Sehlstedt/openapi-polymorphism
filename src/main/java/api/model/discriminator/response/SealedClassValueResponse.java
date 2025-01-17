package api.model.discriminator.response;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public final class SealedClassValueResponse extends SealedClassSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The value to sell", example = "1.1")
    public BigDecimal value;

    public SealedClassValueResponse(BigDecimal value) {
        super(TransferType.VALUE);
        this.value = value;
    }
}
