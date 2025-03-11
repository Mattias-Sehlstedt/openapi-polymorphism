package api.model.jsonType.response;

import api.model.TransferType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonTypeName("PERCENTAGE")
public final class SealedInterfacePercentageResponse extends SealedInterfaceSellBitcoinResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "The percentage to sell", example = "100")
    public BigDecimal percentage;

    @JsonCreator
    public SealedInterfacePercentageResponse(@JsonProperty("percentage") BigDecimal percentage) {
        super(TransferType.PERCENTAGE);
        this.percentage = percentage;
    }
}
