package api.model.array;

import api.model.Amount;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Optional;

public record Analysis(
        @Schema(description = "The name of the analysis", example = "Correctness analysis")
        String name,
        @Schema(description = "A description for the analysis", example = "The analysis describes whether you are correct or not")
        String description,
        @Schema(description = "Whether the analysis is valuable")
        boolean isValuable,
        @Schema(description = "The amount that you have to pay for conducting the analysis")
        Amount amount,
        @Schema(description = "temp", deprecated = true)
        Optional<String> string
) {
}
