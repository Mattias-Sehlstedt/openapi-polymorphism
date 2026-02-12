package api;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApiConflictError(
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                examples = {
                        "ACCOUNT_NOT_CREATED",
                        "BALANCE_INSUFFICIENT",
                        "PAYMENT_ALREADY_UNDER_PROCESSING"})
        String errorCode) {
}
