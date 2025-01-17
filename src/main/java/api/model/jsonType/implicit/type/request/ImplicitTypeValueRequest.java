package api.model.jsonType.implicit.type.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public final class ImplicitTypeValueRequest implements ImplicitTypeSellBitcoinRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    public BigDecimal value;

    @JsonCreator
    ImplicitTypeValueRequest(BigDecimal value) {
        this.value = value;
    }
}
