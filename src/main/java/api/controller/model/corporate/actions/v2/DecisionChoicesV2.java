package api.controller.model.corporate.actions.v2;

import api.controller.model.corporate.actions.v2.option.ElectionOptionV2;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record DecisionChoicesV2(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDate lastDecisionDate,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        ElectionOptionV2 defaultOption,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        List<ElectionOptionV2> electionOptions
) {
}
