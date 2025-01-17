package api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "An amount")
public record Amount(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1.1")
        BigDecimal value,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "SEK", format = "ISO 4217")
        String currency) {
}
