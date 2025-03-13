package controller.model.corporate.actions.v1.decision;

import io.swagger.v3.oas.annotations.media.Schema;

public record ElectionDecision(
        @Schema(example = "1")
        int electionId,
        @Schema(example = "1")
        int quantity,
        @Schema(example = "1")
        int overSubscriptionQuantity,
        @Schema(example = "SEK")
        String currency
) {
}
