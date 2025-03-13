package controller.model.corporate.actions.v2;

import io.swagger.v3.oas.annotations.media.Schema;

public record RatioChangeV2(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        InstrumentV2 newInstrument,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        InstrumentV2 oldInstrument,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        ShareRatioV2 ratio
) {
}
