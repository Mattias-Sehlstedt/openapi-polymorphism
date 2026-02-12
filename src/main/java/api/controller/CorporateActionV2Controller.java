package api.controller;

import api.controller.model.corporate.actions.v1.Instrument;
import api.controller.model.corporate.actions.v1.Ratio;
import api.controller.model.corporate.actions.v1.SharesRatio;
import api.controller.model.corporate.actions.v2.CorporateActionV2;
import api.controller.model.corporate.actions.v2.DecisionChoicesV2;
import api.controller.model.corporate.actions.v2.PaginatedCorporateActionsV2;
import api.controller.model.corporate.actions.v2.decision.ElectionDecisionV2;
import api.controller.model.corporate.actions.v2.option.ElectionOptionV2;
import api.controller.model.corporate.actions.v2.option.ElectionOptionV2DoNotParticipate;
import api.controller.model.corporate.actions.v2.option.ElectionOptionV2Instrument;
import api.controller.model.corporate.actions.v2.option.ElectionOptionV2RatioChange;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/root/v2", produces = "application/json")
public class CorporateActionV2Controller {

    private static final String CORPORATE_ACTION_TAG = "Corporate actions";
    private static final String ACCOUNT_1 = "account-1";
    private static final String ACCOUNT_2 = "account-2";
    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private static final Instrument OLD_INSTRUMENT = new Instrument("OLD_ISIN");
    private static final Instrument NEW_INSTRUMENT = new Instrument("NEW_ISIN");
    private static final SharesRatio RATIO_CHANGE = new SharesRatio(OLD_INSTRUMENT, NEW_INSTRUMENT, new Ratio(1, 1));
    private static final ElectionOptionV2 DEFAULT_CHOICE = new ElectionOptionV2DoNotParticipate(1);
    private static final ElectionOptionV2 OPTION_OVERSUBSCRIBE = new ElectionOptionV2Instrument(2, 10);
    private static final ElectionOptionV2 OPTION_RATIO_CHANGE = new ElectionOptionV2RatioChange(2, RATIO_CHANGE);
    private static final List<ElectionOptionV2> ELECTION_OPTIONS_1 = List.of(
            OPTION_OVERSUBSCRIBE
    );
    private static final List<ElectionOptionV2> ELECTION_OPTIONS_2 = List.of(
            OPTION_RATIO_CHANGE
    );
    private static final DecisionChoicesV2 DECISION_CHOICES_1 = new DecisionChoicesV2(
            LOCAL_DATE, DEFAULT_CHOICE, ELECTION_OPTIONS_1
    );
    private static final DecisionChoicesV2 DECISION_CHOICES_2 = new DecisionChoicesV2(
            LOCAL_DATE, DEFAULT_CHOICE, ELECTION_OPTIONS_2
    );
    private static final CorporateActionV2 CORPORATE_ACTION_1 = new CorporateActionV2(
            1, ACCOUNT_1, Optional.of(DECISION_CHOICES_1)
    );
    private static final CorporateActionV2 CORPORATE_ACTION_2 = new CorporateActionV2(
            2, ACCOUNT_2, Optional.of(DECISION_CHOICES_2)
    );
    private static final CorporateActionV2 CORPORATE_ACTION_3 = new CorporateActionV2(
            2, ACCOUNT_2, Optional.empty()
    );
    private static final List<CorporateActionV2> CORPORATE_ACTIONS = List.of(
            CORPORATE_ACTION_1,
            CORPORATE_ACTION_2,
            CORPORATE_ACTION_3
    );

    @Autowired
    public CorporateActionV2Controller() {
    }

    @Tag(name = CORPORATE_ACTION_TAG)
    @GetMapping(value = "/corporate-actions")
    @ResponseStatus(HttpStatus.OK)
    @Parameter(name = "start", in = ParameterIn.QUERY, example = "1", schema = @Schema(type = "number"))
    @Parameter(name = "count", in = ParameterIn.QUERY, example = "100", schema = @Schema(type = "number"))
    @Parameter(name = "reference", in = ParameterIn.QUERY, example = "account-1", schema = @Schema(type = "string"))
    public Mono<PaginatedCorporateActionsV2> getCorporateActions(
            @RequestParam(name = "start", required = false, defaultValue = "1") @Positive int start,
            @RequestParam(name = "count", required = false, defaultValue = "100") @Max(value = 100) int count,
            @RequestParam(name = "reference", required = true) String reference
    ) {
        return Mono.just(new PaginatedCorporateActionsV2(CORPORATE_ACTIONS, CORPORATE_ACTIONS.size()));
    }

    @Tag(name = CORPORATE_ACTION_TAG)
    @PostMapping(value = "/corporate-actions/{corporate-action-id}")
    @Parameter(name = "corporate-action-id", in = ParameterIn.PATH, example = "1", schema = @Schema(type = "number"))
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> submitElectionDecision(
            @PathVariable(name = "corporate-action-id") int corporateActionId,
            @RequestBody ElectionDecisionV2 electionDecision
    ) {
        return Mono.empty().then();
    }
}
