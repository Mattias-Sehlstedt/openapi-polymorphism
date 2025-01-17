package api.model.array;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record Analyses(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A list of analyses")
        List<Analysis> analyses
) {

}
