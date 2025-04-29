package controller;

import api.model.Amount;
import api.model.array.Analyses;
import api.model.array.Analysis;
import api.model.jsonTypeAndOneOf.Parent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static api.RequestExamples.CHILD_1;
import static api.RequestExamples.CHILD_2;

@RestController
@Validated
@RequestMapping(value = "/root", produces = "application/json")
public class OpenApi_3_1_ArrayList_Controller {

    private static final Amount AMOUNT = new Amount(BigDecimal.valueOf(1), "SEK");

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
                        new Analysis("name1", "description1", false, AMOUNT, Optional.of("temp")),
                        new Analysis("name2", "description2", true, AMOUNT, Optional.of("temp")))
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
                new Analysis("name1", "description1", false, AMOUNT, Optional.of("temp")),
                new Analysis("name2", "description2", true, AMOUNT, Optional.of("temp"))
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
                new Analysis("name1", "description1", false, AMOUNT, Optional.of("temp")),
                new Analysis("name2", "description2", true, AMOUNT, Optional.of("temp"))
        );
    }

    @Tag(name = "DoubleAnnotations")
    @PostMapping(value = "/json-annotation-schema-one-of")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "jsonAnnotationAndSchemaOneOf",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            examples = {
                                    @ExampleObject(name = "Child1", value = CHILD_1),
                                    @ExampleObject(name = "Child2", value = CHILD_2)
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Parent.class))))
            }
    )
    public List<Parent> jsonAnnotationAndSchemaOneOf(@RequestBody @Validated List<Parent> parent) {
        return parent;
    }
}
