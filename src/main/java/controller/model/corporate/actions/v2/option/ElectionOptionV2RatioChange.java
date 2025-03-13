package controller.model.corporate.actions.v2.option;

import controller.model.corporate.actions.v1.SharesRatio;
import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionOptionV2RatioChange(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        int electionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        SharesRatio sharesRatio
) implements ElectionOptionV2 {

    private static final String SCHEMA_NAME = "ElectionOptionV2RatioChange";

    @Override
    public String type() {
        return SCHEMA_NAME;
    }

    @Schema(example = SCHEMA_NAME)
    public String getType() {
        return type();
    }
}