package api.model.array;

import api.model.Amount;
import io.swagger.v3.oas.annotations.media.Schema;

public record Analysis(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The name of the analysis", example = "Correctness analysis")
        String name,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "A description for the analysis", example = "The analysis describes whether you are correct or not")
        String description,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "Whether the analysis is valuable")
        boolean isValuable,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The amount that you have to pay for conducting the analysis")
        Amount amount
) {
}
