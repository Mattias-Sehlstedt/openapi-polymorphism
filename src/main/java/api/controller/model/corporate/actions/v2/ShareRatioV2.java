package api.controller.model.corporate.actions.v2;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The stock split ratio. Follows the principle \"newCount for oldCount\". So 1:2 is regular split while 2:1 is a reverse split.")
public record ShareRatioV2(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        int oldCount,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        int newCount
) {
}