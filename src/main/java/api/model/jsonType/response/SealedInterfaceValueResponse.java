package api.model.jsonType.response;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public final class SealedInterfaceValueResponse extends SealedInterfaceSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The value to sell", example = "1.1")
    public BigDecimal value;

    public SealedInterfaceValueResponse(BigDecimal value) {
        super(TransferType.VALUE);
        this.value = value;
    }
}
