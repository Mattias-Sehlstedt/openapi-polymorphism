package api.controller;

import api.model.error.ApiConflictError;
import api.converters.SellConverter;
import api.model.SellBitcoinRequest;
import api.model.SellBitcoinResponse;
import api.model.discriminator.request.SealedClassSellAmountRequest;
import api.model.discriminator.request.SealedClassSellBitcoinRequest;
import api.model.discriminator.request.SealedClassSellPercentageRequest;
import api.model.discriminator.request.SealedClassSellValueRequest;
import api.model.discriminator.response.SealedClassAmountResponse;
import api.model.discriminator.response.SealedClassPercentageResponse;
import api.model.discriminator.response.SealedClassSellBitcoinResponse;
import api.model.discriminator.response.SealedClassValueResponse;
import api.model.jsonType.explicit.type.request.ExplicitTypeAmountRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypePercentageRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypeSellBitcoinRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypeValueRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypeAmountRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypePercentageRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypeSellBitcoinRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypeValueRequest;
import api.model.jsonType.response.SealedInterfaceAmountResponse;
import api.model.jsonType.response.SealedInterfacePercentageResponse;
import api.model.jsonType.response.SealedInterfaceSellBitcoinResponse;
import api.model.jsonType.response.SealedInterfaceValueResponse;
import api.model.query.AccountIdentifier;
import config.editors.AccountIdentifierEditor;
import domain.model.leakage.TotallyADomainModel;
import domain.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static api.RequestExamples.*;
import static api.controller.ControllerConstants.*;

@RestController
@Validated
@RequestMapping(value = "/root", produces = "application/json")
public class Controller {

    private static final String ACCOUNT_IDENTIFIER_QUERY = "account-identifier";
    private static final String ENVIRONMENT_CLIENT = "client";
    private static final String ENVIRONMENT_SERVER = "server";

    private final Environment environment;
    private final DataService dataService;

    @Autowired
    public Controller(DataService dataService, Environment environment) {
        this.dataService = dataService;
        this.environment = environment;
    }

    @Tag(name = "Json-type request body")
    @PostMapping(value = "/json-type/explicit/sell-bitcoin")
    @ResponseStatus(HttpStatus.CREATED)
    @Parameter(name = IDEMPOTENCY_HEADER, required = true, in = ParameterIn.HEADER, description = IDEMPOTENCY_DESCRIPTION)
    @Parameter(name = TRACING_HEADER, required = true, in = ParameterIn.HEADER, description = TRACING_DESCRIPTION)
    @Parameter(name = AUTH_HEADER, required = true, in = ParameterIn.HEADER, description = AUTH_DESCRIPTION)
    @Operation(description = "Sell your bitcoin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Percentage", value = JSON_TYPE_SELL_PERCENTAGE),
                                    @ExampleObject(name = "Value", value = JSON_TYPE_SELL_VALUE),
                                    @ExampleObject(name = "Amount", value = JSON_TYPE_SELL_AMOUNT)
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiConflictError.class)))
            }
    )
    public Mono<SealedInterfaceSellBitcoinResponse> sellBitcoinExplicit(
            @RequestParam(name = ACCOUNT_IDENTIFIER_QUERY) AccountIdentifier accountIdentifier,
            @RequestBody @Validated ExplicitTypeSellBitcoinRequest request) {
        if (environment.matchesProfiles(ENVIRONMENT_SERVER)) {
            return Mono.just(serverResponse(request));
        } else {
            return dataService.sellInstrument(SellConverter.convertRequest(request))
                    .map(SellConverter::convertToSealedInterfaceResponse);
        }
    }

    @Tag(name = "Json-type request body")
    @PostMapping(value = "/json-type/implicit/sell-bitcoin")
    @ResponseStatus(HttpStatus.CREATED)
    @Parameter(name = IDEMPOTENCY_HEADER, required = true, in = ParameterIn.HEADER, description = IDEMPOTENCY_DESCRIPTION)
    @Parameter(name = TRACING_HEADER, required = true, in = ParameterIn.HEADER, description = TRACING_DESCRIPTION)
    @Parameter(name = AUTH_HEADER, required = true, in = ParameterIn.HEADER, description = AUTH_DESCRIPTION)
    @Operation(description = "Sell your bitcoin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Percentage", value = IMPLICIT_JSON_TYPE_SELL_PERCENTAGE),
                                    @ExampleObject(name = "Value", value = IMPLICIT_JSON_TYPE_SELL_VALUE),
                                    @ExampleObject(name = "Amount", value = IMPLICIT_JSON_TYPE_SELL_AMOUNT)
                            },
                            oneOf = {
                                    @Schema(implementation = ImplicitTypePercentageRequest.class),
                                    @Schema(implementation = ImplicitTypeValueRequest.class),
                                    @Schema(implementation = ImplicitTypeAmountRequest.class)
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiConflictError.class)))
            }
    )
    public Mono<SealedInterfaceSellBitcoinResponse> sellBitcoinImplicit(
            @RequestParam(name = ACCOUNT_IDENTIFIER_QUERY) AccountIdentifier accountIdentifier,
            @RequestBody @Validated ImplicitTypeSellBitcoinRequest request) {
        if (environment.matchesProfiles(ENVIRONMENT_SERVER)) {
            return Mono.just(serverResponse(request));
        } else {
            return dataService.sellInstrument(SellConverter.convertRequest(request))
                    .map(SellConverter::convertToSealedInterfaceResponse);
        }
    }

    @Tag(name = "Discriminated request body")
    @PostMapping(value = "/discriminated/sell-bitcoin")
    @ResponseStatus(HttpStatus.CREATED)
    @Parameter(name = IDEMPOTENCY_HEADER, required = true, in = ParameterIn.HEADER, description = IDEMPOTENCY_DESCRIPTION)
    @Parameter(name = TRACING_HEADER, required = true, in = ParameterIn.HEADER, description = TRACING_DESCRIPTION)
    @Parameter(name = AUTH_HEADER, required = true, in = ParameterIn.HEADER, description = AUTH_DESCRIPTION)
    @Operation(description = "Sell your bitcoin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Percentage", value = JSON_TYPE_SELL_PERCENTAGE),
                                    @ExampleObject(name = "Value", value = JSON_TYPE_SELL_VALUE),
                                    @ExampleObject(name = "Amount", value = JSON_TYPE_SELL_AMOUNT)
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "202", content = @Content(schema = @Schema(implementation = TotallyADomainModel.class))),
                    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiConflictError.class)))
            }
    )
    public Mono<SealedClassSellBitcoinResponse> sellBitcoinDiscriminated(
            @RequestParam(name = ACCOUNT_IDENTIFIER_QUERY) AccountIdentifier accountIdentifier,
            @RequestBody @Validated SealedClassSellBitcoinRequest request) {
        if (environment.matchesProfiles(ENVIRONMENT_SERVER)) {
            return Mono.just(serverResponse(request));
        } else {
            return dataService.sellInstrument(SellConverter.convertRequest(request))
                    .map(SellConverter::convertToSealedClassResponse);
        }
    }

    @Tag(name = "Flat request body")
    @PostMapping(value = "/flattened/sell-bitcoin")
    @ResponseStatus(HttpStatus.CREATED)
    @Parameter(name = IDEMPOTENCY_HEADER, required = true, in = ParameterIn.HEADER, description = IDEMPOTENCY_DESCRIPTION)
    @Parameter(name = TRACING_HEADER, required = true, in = ParameterIn.HEADER, description = TRACING_DESCRIPTION)
    @Parameter(name = AUTH_HEADER, required = true, in = ParameterIn.HEADER, description = AUTH_DESCRIPTION)
    @Operation(description = "Sell your bitcoin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Percentage", value = SELL_PERCENTAGE),
                                    @ExampleObject(name = "Value", value = SELL_VALUE),
                                    @ExampleObject(name = "Amount", value = SELL_AMOUNT)
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ApiConflictError.class)))
            }
    )
    public SellBitcoinResponse sellBitcoin(@RequestBody @Validated SellBitcoinRequest request) {
        //validate that the request only contains one of the three fields
        return new SellBitcoinResponse(
                request.percentage(),
                request.value(),
                request.amount()
        );
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(AccountIdentifier.class, new AccountIdentifierEditor());
    }

    private static SealedInterfaceSellBitcoinResponse serverResponse(ExplicitTypeSellBitcoinRequest request) {
        return switch (request) {
            case ExplicitTypePercentageRequest percentageRequest ->
                    new SealedInterfacePercentageResponse(percentageRequest.percentage);
            case ExplicitTypeValueRequest valueRequest -> new SealedInterfaceValueResponse(valueRequest.value);
            case ExplicitTypeAmountRequest amountRequest -> new SealedInterfaceAmountResponse(amountRequest.amount);
        };
    }

    private static SealedInterfaceSellBitcoinResponse serverResponse(ImplicitTypeSellBitcoinRequest request) {
        return switch (request) {
            case ImplicitTypePercentageRequest percentageRequest ->
                    new SealedInterfacePercentageResponse(percentageRequest.percentage);
            case ImplicitTypeValueRequest valueRequest -> new SealedInterfaceValueResponse(valueRequest.value);
            case ImplicitTypeAmountRequest amountRequest -> new SealedInterfaceAmountResponse(amountRequest.amount);
        };
    }

    private static SealedClassSellBitcoinResponse serverResponse(SealedClassSellBitcoinRequest request) {
        return switch (request) {
            case SealedClassSellPercentageRequest percentageRequest ->
                    new SealedClassPercentageResponse(percentageRequest.percentage);
            case SealedClassSellValueRequest valueRequest -> new SealedClassValueResponse(valueRequest.value);
            case SealedClassSellAmountRequest amountRequest -> new SealedClassAmountResponse(amountRequest.amount);
        };
    }
}
