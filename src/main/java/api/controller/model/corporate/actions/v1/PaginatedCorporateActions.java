package api.controller.model.corporate.actions.v1;

import java.util.List;

public record PaginatedCorporateActions(
        List<CorporateAction> corporateActions,
        int totalElements
) {
}