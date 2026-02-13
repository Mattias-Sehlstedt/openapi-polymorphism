package api.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static api.controller.ControllerConstants.*;
import static api.controller.ControllerConstants.AUTH_DESCRIPTION;

@Retention(RetentionPolicy.RUNTIME)
@Parameter(name = IDEMPOTENCY_HEADER, required = true, in = ParameterIn.HEADER, description = IDEMPOTENCY_DESCRIPTION, schema = @Schema(implementation = String.class))
@Parameter(name = TRACING_HEADER, required = true, in = ParameterIn.HEADER, description = TRACING_DESCRIPTION, schema = @Schema(implementation = String.class))
@Parameter(name = AUTH_HEADER, required = true, in = ParameterIn.HEADER, description = AUTH_DESCRIPTION, schema = @Schema(implementation = String.class))
public @interface CommonParameters {
}


