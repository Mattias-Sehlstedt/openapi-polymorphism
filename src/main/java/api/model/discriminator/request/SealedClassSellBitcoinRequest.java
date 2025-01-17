package api.model.discriminator.request;

import api.model.TransferType;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(discriminatorProperty = "type",
        discriminatorMapping = {
                @DiscriminatorMapping(value = "PERCENTAGE", schema = SealedClassSellPercentageRequest.class),
                @DiscriminatorMapping(value = "VALUE", schema = SealedClassSellValueRequest.class),
                @DiscriminatorMapping(value = "AMOUNT", schema = SealedClassSellAmountRequest.class)
        },
        oneOf = {
                SealedClassSellPercentageRequest.class,
                SealedClassSellValueRequest.class,
                SealedClassSellAmountRequest.class
        }
)
public abstract sealed class SealedClassSellBitcoinRequest permits SealedClassSellAmountRequest, SealedClassSellPercentageRequest, SealedClassSellValueRequest {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private final TransferType type;

    protected SealedClassSellBitcoinRequest(TransferType type) {
        this.type = type;
    }
}
