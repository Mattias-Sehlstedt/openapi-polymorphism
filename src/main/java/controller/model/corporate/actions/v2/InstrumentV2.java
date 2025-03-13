package controller.model.corporate.actions.v2;

import io.swagger.v3.oas.annotations.media.Schema;

public record InstrumentV2(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ISIN")
        String isin
) {
}
