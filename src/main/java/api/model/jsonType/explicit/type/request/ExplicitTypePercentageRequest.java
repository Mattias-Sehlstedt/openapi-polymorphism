package api.model.jsonType.explicit.type.request;

import api.model.TransferType;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public final class ExplicitTypePercentageRequest extends ExplicitTypeSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The percentage to sell", example = "100")
    @NotNull
    public BigDecimal percentage;

    @JsonCreator
    public ExplicitTypePercentageRequest(BigDecimal percentage) {
        super(TransferType.PERCENTAGE);
        this.percentage = percentage;
    }
}
