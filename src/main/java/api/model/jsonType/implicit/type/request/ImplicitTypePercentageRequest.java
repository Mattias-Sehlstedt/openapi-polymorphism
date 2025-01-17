package api.model.jsonType.implicit.type.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public final class ImplicitTypePercentageRequest implements ImplicitTypeSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The percentage to sell", example = "100")
    @NotNull
    public BigDecimal percentage;

    @JsonCreator
    public ImplicitTypePercentageRequest(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
