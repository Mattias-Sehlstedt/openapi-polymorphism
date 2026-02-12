package api.controller.model.corporate.actions.v2.decision;

import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionDecisionV2Quantity(
        int electionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        int quantity
) implements ElectionDecisionV2 {

    private static final String SCHEMA_NAME = "ElectionDecisionV2Quantity";

    @Override
    public String type() {
        return SCHEMA_NAME;
    }

    @Schema(example = SCHEMA_NAME)
    public String getType() {
        return type();
    }
}
