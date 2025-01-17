package api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;

@Schema(description = "The quantity of Bitcoin to sell. Can either be a percentage, a value (number of bitcoins) or an amount (the value to sell given in another currency).")
public record SellBitcoinResponse(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Mutually exclusive with value and amount")
        @Nullable
        BigDecimal percentage,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Mutually exclusive with percentage and amount")
        @Nullable
        BigDecimal value,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Mutually exclusive with value and percentage")
        @Nullable
        Amount amount
) {
}
