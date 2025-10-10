package api.model.query;

import api.annotation.ClearingNumber;
import io.swagger.v3.oas.annotations.media.Schema;

public class TwoPartAccountIdentifier {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "123")
    @ClearingNumber
    public String clearingNumber;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "456789", pattern = "^\\d{6}$")
    public String accountNumber;
}
