package adapter;

import api.model.Amount;
import api.model.discriminator.request.SealedClassSellAmountRequest;
import api.model.discriminator.request.SealedClassSellBitcoinRequest;
import api.model.discriminator.request.SealedClassSellPercentageRequest;
import api.model.discriminator.request.SealedClassSellValueRequest;
import api.model.discriminator.response.SealedClassAmountResponse;
import api.model.discriminator.response.SealedClassPercentageResponse;
import api.model.discriminator.response.SealedClassSellBitcoinResponse;
import api.model.discriminator.response.SealedClassValueResponse;
import api.model.jsonType.explicit.type.request.ExplicitTypeAmountRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypePercentageRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypeSellBitcoinRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypeValueRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypeAmountRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypePercentageRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypeSellBitcoinRequest;
import api.model.jsonType.implicit.type.request.ImplicitTypeValueRequest;
import api.model.jsonType.response.SealedInterfaceAmountResponse;
import api.model.jsonType.response.SealedInterfacePercentageResponse;
import api.model.jsonType.response.SealedInterfaceSellBitcoinResponse;
import api.model.jsonType.response.SealedInterfaceValueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import polymorphism.api.DiscriminatedRequestBodyApi;
import polymorphism.api.FlatRequestBodyApi;
import polymorphism.api.JsonTypeRequestBodyApi;
import polymorphism.model.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SellBitcoinAdapter {

    private final FlatRequestBodyApi flatRequestBodyApi;
    private final JsonTypeRequestBodyApi jsonTypeRequestBodyApi;
    private final DiscriminatedRequestBodyApi discriminatedRequestBodyApi;

    @Autowired
    SellBitcoinAdapter(FlatRequestBodyApi flatRequestBodyApi,
                       JsonTypeRequestBodyApi jsonTypeRequestBodyApi,
                       DiscriminatedRequestBodyApi discriminatedRequestBodyApi) {
        this.flatRequestBodyApi = flatRequestBodyApi;
        this.jsonTypeRequestBodyApi = jsonTypeRequestBodyApi;
        this.discriminatedRequestBodyApi = discriminatedRequestBodyApi;
    }

    private static final AmountDTO AMOUNT_DTO = new AmountDTO()
            .currency("SEK")
            .value(BigDecimal.valueOf(10));

    public void sellBitcoin() {
        flatRequestBodyApi.sellBitcoin(new SellBitcoinRequestDTO()
                .percentage(BigDecimal.valueOf(10))
                .value(BigDecimal.valueOf(10))
                .amount(AMOUNT_DTO));
    }

    public Mono<SealedInterfaceSellBitcoinResponse> sellBitcoinExplicit(ExplicitTypeSellBitcoinRequest request) {
        return jsonTypeRequestBodyApi.sellBitcoinExplicit(new JsonTypeRequestBodyApi.SellBitcoinExplicitRequest()
                        .sellBitcoinExplicitRequestDTO(convertRequest(request))
                        .schema(List.of(new SortDTO().type("type").direction("direction"), new SortDTO().type("type").direction("direction")))
                        .content(List.of(new SortDTO().type("type").direction("direction"), new SortDTO().type("type").direction("direction"))))
                .map(this::convertResponse);
    }

    public Mono<SealedInterfaceSellBitcoinResponse> sellBitcoinImplicit(ImplicitTypeSellBitcoinRequest request) {
        return jsonTypeRequestBodyApi.sellBitcoinImplicit(convertRequest(request))
                .map(this::convertResponse);
    }

    public Mono<SealedClassSellBitcoinResponse> sellBitcoinDiscriminated(SealedClassSellBitcoinRequest request) {
        return discriminatedRequestBodyApi.sellBitcoinDiscriminated(convertRequest(request))
                .map(this::convertResponse);
    }

    private SealedInterfaceSellBitcoinResponse convertResponse(SellBitcoinImplicit201ResponseDTO sellBitcoinImplicit201ResponseDTO) {
        return switch (sellBitcoinImplicit201ResponseDTO) {
            case SealedInterfaceAmountResponseDTO sealedInterfaceAmountResponseDTO ->
                    new SealedInterfaceAmountResponse(convertModel(sealedInterfaceAmountResponseDTO.getAmount()));
            case SealedInterfacePercentageResponseDTO sealedInterfacePercentageResponseDTO ->
                    new SealedInterfacePercentageResponse(sealedInterfacePercentageResponseDTO.getPercentage());
            case SealedInterfaceValueResponseDTO sealedInterfaceValueResponseDTO ->
                    new SealedInterfaceValueResponse(sealedInterfaceValueResponseDTO.getValue());
            default -> throw new IllegalStateException("Unexpected value: " + sellBitcoinImplicit201ResponseDTO);
        };
    }

    private SealedClassSellBitcoinResponse convertResponse(SealedClassSellBitcoinResponseDTO request) {
        return switch (request) {
            case SealedClassAmountResponseDTO sealedClassAmountResponseDTO ->
                    new SealedClassAmountResponse(convertModel(sealedClassAmountResponseDTO.getAmount()));
            case SealedClassPercentageResponseDTO sealedClassPercentageResponseDTO ->
                    new SealedClassPercentageResponse(sealedClassPercentageResponseDTO.getPercentage());
            case SealedClassValueResponseDTO sealedClassValueResponseDTO ->
                    new SealedClassValueResponse(sealedClassValueResponseDTO.getValue());
            default -> throw new IllegalStateException("Unexpected value: " + request);
        };
    }

    private SellBitcoinExplicitRequestDTO convertRequest(ExplicitTypeSellBitcoinRequest request) {
        return switch (request) {
            case ExplicitTypeAmountRequest explicitTypeAmountRequest -> new ExplicitTypeAmountRequestDTO()
                    .amount(convertModel(explicitTypeAmountRequest.amount));
            case ExplicitTypePercentageRequest explicitTypePercentageRequest ->
                    new ExplicitTypePercentageRequestDTO().percentage(explicitTypePercentageRequest.percentage);
            case ExplicitTypeValueRequest explicitTypeValueRequest ->
                    new ExplicitTypeValueRequestDTO().value(explicitTypeValueRequest.value);
        };
    }

    private SellBitcoinImplicitRequestDTO convertRequest(ImplicitTypeSellBitcoinRequest request) {
        return switch (request) {
            case ImplicitTypeAmountRequest implicitTypeAmountRequest -> new ImplicitTypeAmountRequestDTO()
                    .amount(convertModel(implicitTypeAmountRequest.amount));
            case ImplicitTypePercentageRequest implicitTypePercentageRequest ->
                    new ImplicitTypePercentageRequestDTO().percentage(implicitTypePercentageRequest.percentage);
            case ImplicitTypeValueRequest implicitTypeValueRequest ->
                    new ImplicitTypeValueRequestDTO().value(implicitTypeValueRequest.value);
        };
    }

    private SealedClassSellBitcoinRequestDTO convertRequest(SealedClassSellBitcoinRequest request) {
        return switch (request) {
            case SealedClassSellAmountRequest sealedClassSellAmountRequest -> new SealedClassSellAmountRequestDTO()
                    .type(TransferTypeDTO.AMOUNT)
                    .amount(convertModel(sealedClassSellAmountRequest.amount));
            case SealedClassSellPercentageRequest sealedClassSellPercentageRequest ->
                    new SealedClassSellPercentageRequestDTO()
                            .type(TransferTypeDTO.PERCENTAGE)
                            .percentage(sealedClassSellPercentageRequest.percentage);
            case SealedClassSellValueRequest sealedClassSellValueRequest -> new SealedClassSellValueRequestDTO()
                    .type(TransferTypeDTO.VALUE)
                    .value(sealedClassSellValueRequest.value);
        };
    }

    private AmountDTO convertModel(Amount amount) {
        return new AmountDTO().currency(amount.currency()).value(amount.value());
    }

    private Amount convertModel(AmountDTO amount) {
        return new Amount(amount.getValue(), amount.getCurrency());
    }
}
