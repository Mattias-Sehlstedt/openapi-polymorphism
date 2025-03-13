package controller.model.corporate.actions.v2.option;

import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionOptionV2DoNotParticipate(
        int electionId
) implements ElectionOptionV2 {

    private static final String SCHEMA_NAME = "ElectionOptionV2DoNotParticipate";

    @Override
    public String type() {
        return SCHEMA_NAME;
    }

    @Schema(example = SCHEMA_NAME)
    public String getType() {
        return type();
    }
}