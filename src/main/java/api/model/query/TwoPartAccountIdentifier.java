package api.model.query;

import io.swagger.v3.oas.annotations.media.Schema;

public class TwoPartAccountIdentifier {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "123", pattern = "^\\d{3}$")
    public String clearingNumber;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "456789", pattern = "^\\d{6}$")
    public String accountNumber;
}
