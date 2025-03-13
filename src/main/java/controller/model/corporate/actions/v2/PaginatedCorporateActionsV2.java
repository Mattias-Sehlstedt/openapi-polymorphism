package controller.model.corporate.actions.v2;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PaginatedCorporateActionsV2(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        List<CorporateActionV2> corporateActions,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        int totalElements
) {
}