package api.controller.model.corporate.actions.v2.option;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ElectionOptionV2Instrument")
public record ElectionOptionV2Instrument(
        int electionId,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        Integer quantity
) implements ElectionOptionV2 {

    private static final String SCHEMA_NAME = "ElectionOptionV2Instrument";

    @Override
    public String type() {
        return SCHEMA_NAME;
    }

    @Schema(example = SCHEMA_NAME)
    public String getType() {
        return type();
    }
}