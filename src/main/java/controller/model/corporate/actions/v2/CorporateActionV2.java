package controller.model.corporate.actions.v2;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

public record CorporateActionV2(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        int corporateActionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "account-1")
        String account,
        @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Optional<DecisionChoicesV2> decisionChoices
) {
}
