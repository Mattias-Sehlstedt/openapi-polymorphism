package controller;

import api.model.Amount;
import controller.model.corporate.actions.v1.*;
import controller.model.corporate.actions.v1.decision.ElectionDecision;
import controller.model.corporate.actions.v1.option.ElectionOption;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/root/v1", produces = "application/json")
public class CorporateActionController {

    private static final String CORPORATE_ACTION_TAG = "Corporate actions";
    private static final String ACCOUNT_1 = "account-1";
    private static final String ACCOUNT_2 = "account-2";
    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private static final Instrument OLD_INSTRUMENT = new Instrument("OLD_ISIN");
    private static final Instrument NEW_INSTRUMENT = new Instrument("NEW_ISIN");
    private static final SharesRatio SHARES_RATIO = new SharesRatio(OLD_INSTRUMENT, NEW_INSTRUMENT, new Ratio(1, 1));
    private static final Amount PRICE = new Amount(BigDecimal.valueOf(10), "SEK");
    private static final InstrumentAllocation INSTRUMENT_ACTION = new InstrumentAllocation(OLD_INSTRUMENT, PRICE, SHARES_RATIO);
    private static final ElectionOption DEFAULT_CHOICE = new ElectionOption(
            1,
            true,
            false,
            true,
            null,
            null,
            false,
            null,
            null
    );
    private static final ElectionOption OPTION_OVERSUBSCRIBE = new ElectionOption(
            2,
            false,
            true,
            false,
            null,
            10,
            true,
            BigDecimal.valueOf(1.5),
            null
    );
    private static final ElectionOption OPTION_RATIO_CHANGE = new ElectionOption(
            2,
            false,
            false,
            false,
            null,
            10,
            false,
            null,
            List.of(INSTRUMENT_ACTION)
    );
    private static final List<ElectionOption> ELECTION_OPTIONS_1 = List.of(
            DEFAULT_CHOICE,
            OPTION_OVERSUBSCRIBE
    );
    private static final List<ElectionOption> ELECTION_OPTIONS_2 = List.of(
            DEFAULT_CHOICE,
            OPTION_RATIO_CHANGE
    );
    private static final CorporateAction CORPORATE_ACTION_1 = new CorporateAction(
            1, ACCOUNT_1, LOCAL_DATE, true, ELECTION_OPTIONS_1
    );
    private static final CorporateAction CORPORATE_ACTION_2 = new CorporateAction(
            2, ACCOUNT_2, LOCAL_DATE, true, ELECTION_OPTIONS_2
    );
    private static final CorporateAction CORPORATE_ACTION_3 = new CorporateAction(
            2, ACCOUNT_2, null, false, null
    );
    private static final List<CorporateAction> CORPORATE_ACTIONS = List.of(
            CORPORATE_ACTION_1,
            CORPORATE_ACTION_2,
            CORPORATE_ACTION_3
    );

    @Autowired
    public CorporateActionController() {
    }

    @Tag(name = CORPORATE_ACTION_TAG)
    @GetMapping(value = "/corporate-actions")
    @ResponseStatus(HttpStatus.OK)
    @Parameter(name = "start", in = ParameterIn.QUERY, example = "1", schema = @Schema(type = "number"))
    @Parameter(name = "count", in = ParameterIn.QUERY, example = "100", schema = @Schema(type = "number"))
    @Parameter(name = "reference", in = ParameterIn.QUERY, example = "account-1", schema = @Schema(type = "string"))
    public Mono<PaginatedCorporateActions> getCorporateActions(
            @RequestParam(name = "start", required = false, defaultValue = "1") @Positive int start,
            @RequestParam(name = "count", required = false, defaultValue = "100") @Max(value = 100) int count,
            @RequestParam(name = "reference", required = true) String reference
    ) {
        return Mono.just(new PaginatedCorporateActions(CORPORATE_ACTIONS, CORPORATE_ACTIONS.size()));
    }

    @Tag(name = CORPORATE_ACTION_TAG)
    @PostMapping(value = "/corporate-actions/{corporate-action-id}")
    @Parameter(name = "corporate-action-id", in = ParameterIn.PATH, example = "1", schema = @Schema(type = "number"))
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> submitElectionDecision(
            @PathVariable(name = "corporate-action-id") int corporateActionId,
            @RequestBody ElectionDecision electionDecision
    ) {
        return Mono.empty().then();
    }
}
