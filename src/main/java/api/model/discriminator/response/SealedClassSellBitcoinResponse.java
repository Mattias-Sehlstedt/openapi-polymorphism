package api.model.discriminator.response;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(discriminatorProperty = "type",
        discriminatorMapping = {
                @DiscriminatorMapping(value = "AMOUNT", schema = SealedClassAmountResponse.class),
                @DiscriminatorMapping(value = "PERCENTAGE", schema = SealedClassPercentageResponse.class),
                @DiscriminatorMapping(value = "VALUE", schema = SealedClassValueResponse.class),
        },
        oneOf = {
                SealedClassAmountResponse.class,
                SealedClassPercentageResponse.class,
                SealedClassValueResponse.class
        }
)
public abstract sealed class SealedClassSellBitcoinResponse permits SealedClassAmountResponse, SealedClassPercentageResponse, SealedClassValueResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private final TransferType type;

    protected SealedClassSellBitcoinResponse(TransferType type) {
        this.type = type;
    }
}
