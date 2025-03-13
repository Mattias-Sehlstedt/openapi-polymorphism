package controller.model.corporate.actions.v1;

import controller.model.corporate.actions.v1.option.ElectionOption;

import java.time.LocalDate;
import java.util.List;

public record CorporateAction(
        int corporateActionId,
        String account,
        LocalDate lastDecisionDate,
        boolean hasElection,
        List<ElectionOption> electionOptions
) {
}
