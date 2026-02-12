package api.controller.model.corporate.actions.v2.decision;

import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionDecisionV2FixedElection(
        int electionId
) implements ElectionDecisionV2 {

    @Override
    public String type() {
        return "ElectionDecisionV2FixedElection";
    }

    @Schema(example = "ElectionDecisionV2FixedElection")
    public String getType() {
        return type();
    }
}
