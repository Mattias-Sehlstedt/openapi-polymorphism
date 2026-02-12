package api.controller;

import api.model.query.Sort;
import api.model.query.SortList;
import api.model.query.TwoPartAccountIdentifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.editors.SortEditor;
import config.editors.SortListEditor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@RestController
@Validated
@RequestMapping(value = "/root/query", produces = "application/json")
public class CustomQueryParameterController {

    @Autowired
    public CustomQueryParameterController() {
    }

    @Tag(name = "Sort with Json-query parameter")
    @GetMapping(value = "/sort-with-custom-query-parameter")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Sell your bitcoin", responses = {
            @ApiResponse(responseCode = "200")
    }, extensions = @Extension(properties = @ExtensionProperty(name = "x-internal", value = "true")))
    @Parameter(name = "schema", in = ParameterIn.QUERY, explode = Explode.FALSE, array = @ArraySchema(schema = @Schema(implementation = Sort.class)))
    @Parameter(name = "list-of-objects", in = ParameterIn.QUERY, content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sort.class))))
    @Parameter(name = "object-list", in = ParameterIn.QUERY, content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sort.class))))
    @Parameter(name = "account-identifier", in = ParameterIn.QUERY, schema = @Schema(implementation = TwoPartAccountIdentifier.class))
    public Mono<Void> customQueryParameter(
            @RequestParam(name = "schema", required = false) List<String> schemaSort,
            @RequestParam(name = "list-of-objects", required = false) List<Sort> listOfObjects,
            @RequestParam(name = "object-list", required = false) SortList objectList,
            @RequestParam(name = "accountNumber", required = false) @Schema(hidden = true) String accountNumber,
            @RequestParam(name = "clearingNumber", required = false) @Schema(hidden = true) String clearingNumber
    ) {
        if (Objects.nonNull(objectList)) {
            objectList.sortList.forEach(System.out::println);
        }
        if (Objects.nonNull(listOfObjects)) {
            listOfObjects.forEach(System.out::println);
        }
        if (Objects.nonNull(schemaSort)) {
            schemaSort.forEach(System.out::println);
        }
        return Mono.empty();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Sort.class, new SortEditor(new ObjectMapper()));
        binder.registerCustomEditor(SortList.class, new SortListEditor(new ObjectMapper()));
    }
}
