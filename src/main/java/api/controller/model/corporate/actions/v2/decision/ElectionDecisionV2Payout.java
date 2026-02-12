package api.controller.model.corporate.actions.v2.decision;

import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionDecisionV2Payout(
        int electionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "SEK", format = "ISO 4217")
        String currency
) implements ElectionDecisionV2 {

    @Override
    public String type() {
        return "ElectionDecisionV2Payout";
    }

    @Schema(example = "ElectionDecisionV2Payout")
    public String getType() {
        return type();
    }
}
