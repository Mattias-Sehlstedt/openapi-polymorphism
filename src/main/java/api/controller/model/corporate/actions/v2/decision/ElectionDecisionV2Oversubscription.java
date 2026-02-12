package api.controller.model.corporate.actions.v2.decision;

import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionDecisionV2Oversubscription(
        int electionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        int quantity
) implements ElectionDecisionV2 {

    @Override
    public String type() {
        return "ElectionDecisionV2Oversubscription";
    }

    @Schema(example = "ElectionDecisionV2Oversubscription")
    public String getType() {
        return type();
    }
}
