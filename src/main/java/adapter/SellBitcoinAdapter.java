package adapter;

import domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import polymorphism.api.DiscriminatedRequestBodyApi;
import polymorphism.api.FlatRequestBodyApi;
import polymorphism.api.JsonTypeRequestBodyApi;
import polymorphism.model.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Component
public class SellBitcoinAdapter {

    private static final Instrument NO_INSTRUMENT = null;

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

    public Mono<SellInstrument> sellBitcoinExplicit(SellInstrument request) {
        return jsonTypeRequestBodyApi.sellBitcoinExplicit(convertToExplicitRequest(request))
                .map(this::convertResponse);
    }

    public Mono<SellInstrument> sellBitcoinImplicit(SellInstrument request) {
        return jsonTypeRequestBodyApi.sellBitcoinImplicit(convertToImplicitRequest(request))
                .map(this::convertResponse);
    }

    public Mono<SellInstrument> sellBitcoinDiscriminated(SellInstrument request) {
        return discriminatedRequestBodyApi.sellBitcoinDiscriminated(convertToDiscriminatedRequest(request))
                .map(this::convertResponse);
    }

    private SellInstrument convertResponse(SellBitcoinImplicit201ResponseDTO sellBitcoinImplicit201ResponseDTO) {
        return switch (sellBitcoinImplicit201ResponseDTO) {
            case SealedInterfaceAmountResponseDTO sealedInterfaceAmountResponseDTO ->
                    new SellAmount(NO_INSTRUMENT, convertModel(sealedInterfaceAmountResponseDTO.getAmount()));
            case SealedInterfacePercentageResponseDTO sealedInterfacePercentageResponseDTO ->
                    new SellPercentage(NO_INSTRUMENT, new Percentage(sealedInterfacePercentageResponseDTO.getPercentage()));
            case SealedInterfaceValueResponseDTO sealedInterfaceValueResponseDTO ->
                    new SellValue(NO_INSTRUMENT, new Shares(sealedInterfaceValueResponseDTO.getValue()));
            default -> throw new IllegalStateException("Unexpected value: " + sellBitcoinImplicit201ResponseDTO);
        };
    }

    private SellInstrument convertResponse(SealedClassSellBitcoinResponseDTO request) {
        return switch (request) {
            case SealedClassAmountResponseDTO sealedClassAmountResponseDTO ->
                    new SellAmount(NO_INSTRUMENT, convertModel(sealedClassAmountResponseDTO.getAmount()));
            case SealedClassPercentageResponseDTO sealedClassPercentageResponseDTO ->
                    new SellPercentage(NO_INSTRUMENT, new Percentage(sealedClassPercentageResponseDTO.getPercentage()));
            case SealedClassValueResponseDTO sealedClassValueResponseDTO ->
                    new SellValue(NO_INSTRUMENT, new Shares(sealedClassValueResponseDTO.getValue()));
            default -> throw new IllegalStateException("Unexpected value: " + request);
        };
    }

    private SellBitcoinExplicitRequestDTO convertToExplicitRequest(SellInstrument request) {
        return switch (request) {
            case SellAmount sellAmount -> new ExplicitTypeAmountRequestDTO()
                    .amount(convertModel(sellAmount.amount()));
            case SellPercentage sellPercentage ->
                    new ExplicitTypePercentageRequestDTO().percentage(sellPercentage.percentage().value());
            case SellValue sellValue ->
                    new ExplicitTypeValueRequestDTO().value(sellValue.shares().value());
        };
    }

    private SellBitcoinImplicitRequestDTO convertToImplicitRequest(SellInstrument request) {
        return switch (request) {
            case SellAmount sellAmount -> new ImplicitTypeAmountRequestDTO()
                    .amount(convertModel(sellAmount.amount()));
            case SellPercentage sellPercentage ->
                    new ImplicitTypePercentageRequestDTO().percentage(sellPercentage.percentage().value());
            case SellValue sellValue ->
                    new ImplicitTypeValueRequestDTO().value(sellValue.shares().value());
        };
    }

    private SealedClassSellBitcoinRequestDTO convertToDiscriminatedRequest(SellInstrument request) {
        return switch (request) {
            case SellAmount sellAmount -> new SealedClassSellAmountRequestDTO()
                    .type(TransferTypeDTO.AMOUNT)
                    .amount(convertModel(sellAmount.amount()));
            case SellPercentage sellPercentage ->
                    new SealedClassSellPercentageRequestDTO()
                            .type(TransferTypeDTO.PERCENTAGE)
                            .percentage(sellPercentage.percentage().value());
            case SellValue sellValue -> new SealedClassSellValueRequestDTO()
                    .type(TransferTypeDTO.VALUE)
                    .value(sellValue.shares().value());
        };
    }

    private AmountDTO convertModel(Amount amount) {
        return new AmountDTO().currency(amount.currency().getCurrencyCode()).value(amount.value());
    }

    private Amount convertModel(AmountDTO amount) {
        return new Amount(Currency.getInstance(amount.getCurrency()), amount.getValue());
    }
}
