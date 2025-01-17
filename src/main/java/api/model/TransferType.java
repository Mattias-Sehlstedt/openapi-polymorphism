package api.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "description", example = "PERCENTAGE", enumAsRef = true)
public enum TransferType {
    PERCENTAGE,
    AMOUNT,
    VALUE;

    public static TransferType convert() {
        return PERCENTAGE;
    }
}
