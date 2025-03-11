package api.model.jsonType.response;

import api.model.TransferType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonTypeName("VALUE")
public final class SealedInterfaceValueResponse extends SealedInterfaceSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The value to sell", example = "1.1")
    public BigDecimal value;

    @JsonCreator
    public SealedInterfaceValueResponse(@JsonProperty("value") BigDecimal value) {
        super(TransferType.VALUE);
        this.value = value;
    }
}
