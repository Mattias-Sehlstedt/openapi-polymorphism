package controller.model.corporate.actions.v2.option;

import api.model.Amount;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ElectionOptionV2Cash(
        int electionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        List<Amount> cashAllocations
) implements ElectionOptionV2 {

    private static final String SCHEMA_NAME = "ElectionOptionV2Cash";

    @Override
    public String type() {
        return SCHEMA_NAME;
    }

    @Schema(example = SCHEMA_NAME)
    public String getType() {
        return type();
    }
}