package controller;

import api.model.Amount;
import api.model.array.Analyses;
import api.model.array.Analysis;
import api.model.jsonTypeAndOneOf.Parent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/root", produces = "application/json")
public class OpenApi_3_1_ArrayList_Controller {

    @Autowired
    public OpenApi_3_1_ArrayList_Controller() {
    }

    @Tag(name = "Analyses")
    @GetMapping(value = "/analyses")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get analyses",
            responses = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public Mono<Analyses> getAnalyses() {
        return Mono.just(new Analyses(List.of(
                        new Analysis("name1", "description1", false, new Amount(BigDecimal.valueOf(1), "SEK")),
                        new Analysis("name2", "description2", true, new Amount(BigDecimal.valueOf(1), "SEK")))
                )
        );
    }

    @Tag(name = "Analyses")
    @GetMapping(value = "/v2/analyses")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get analyses",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Analysis.class))))
            }
    )
    public Flux<Analysis> getAnalysesV2() {
        return Flux.just(
                new Analysis("name1", "description1", false, new Amount(BigDecimal.valueOf(1), "SEK")),
                new Analysis("name2", "description2", true, new Amount(BigDecimal.valueOf(1), "SEK"))
        );
    }

    @Tag(name = "Analyses")
    @GetMapping(value = "/v3/analyses")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get analyses",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Analysis.class))))
            }
    )
    public List<Analysis> getAnalysesV3() {
        return List.of(
                new Analysis("name1", "description1", false, new Amount(BigDecimal.valueOf(1), "SEK")),
                new Analysis("name2", "description2", true, new Amount(BigDecimal.valueOf(1), "SEK"))
        );
    }

    @Tag(name = "DoubleAnnotations")
    @GetMapping(value = "/json-annotation-schema-one-of")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "jsonAnnotationAndSchemaOneOf",
            responses = {
                    @ApiResponse(responseCode = "200")
            }
    )
    public Parent jsonAnnotationAndSchemaOneOf() {
        return null;
    }
}
