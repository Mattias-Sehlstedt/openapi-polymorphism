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
public final class ExplicitTypeValueRequest extends ExplicitTypeSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    public BigDecimal value;

    @JsonCreator
    ExplicitTypeValueRequest(BigDecimal value) {
        super(TransferType.VALUE);
        this.value = value;
    }
}
