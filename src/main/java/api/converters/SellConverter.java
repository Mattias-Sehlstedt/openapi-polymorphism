package api.converters;

import api.model.discriminator.request.SealedClassSellAmountRequest;
import api.model.discriminator.request.SealedClassSellBitcoinRequest;
import api.model.discriminator.request.SealedClassSellPercentageRequest;
import api.model.discriminator.request.SealedClassSellValueRequest;
import api.model.discriminator.response.SealedClassAmountResponse;
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
import domain.model.*;

import java.util.Currency;

public class SellConverter {

    private static final Instrument NO_INSTRUMENT = null;

    public static SellInstrument convertRequest(ExplicitTypeSellBitcoinRequest request) {
        return switch (request) {
            case ExplicitTypeAmountRequest explicitTypeAmountRequest ->
                    new SellAmount(NO_INSTRUMENT, convertModel(explicitTypeAmountRequest.amount));
            case ExplicitTypePercentageRequest explicitTypePercentageRequest ->
                    new SellPercentage(NO_INSTRUMENT, new Percentage(explicitTypePercentageRequest.percentage));
            case ExplicitTypeValueRequest explicitTypeValueRequest ->
                    new SellValue(NO_INSTRUMENT, new Shares(explicitTypeValueRequest.value));
        };
    }

    public static SealedInterfaceSellBitcoinResponse convertToSealedInterfaceResponse(SellInstrument response) {
        return switch (response) {
            case SellAmount sellAmount -> new SealedInterfaceAmountResponse(convertModel(sellAmount.amount()));
            case SellPercentage sellPercentage -> new SealedInterfacePercentageResponse(sellPercentage.percentage().value());
            case SellValue sellValue -> new SealedInterfaceValueResponse(sellValue.shares().value());
        };
    }

    public static SellInstrument convertRequest(ImplicitTypeSellBitcoinRequest request) {
        return switch (request) {
            case ImplicitTypeAmountRequest implicitTypeAmountRequest ->
                    new SellAmount(NO_INSTRUMENT, convertModel(implicitTypeAmountRequest.amount));
            case ImplicitTypePercentageRequest implicitTypePercentageRequest ->
                    new SellPercentage(NO_INSTRUMENT, new Percentage(implicitTypePercentageRequest.percentage));
            case ImplicitTypeValueRequest implicitTypeValueRequest ->
                    new SellValue(NO_INSTRUMENT, new Shares(implicitTypeValueRequest.value));
        };
    }

    public static SellInstrument convertRequest(SealedClassSellBitcoinRequest request) {
        return switch (request) {
            case SealedClassSellAmountRequest sealedClassSellAmountRequest ->
                    new SellAmount(NO_INSTRUMENT, convertModel(sealedClassSellAmountRequest.amount));
            case SealedClassSellPercentageRequest sealedClassSellPercentageRequest ->
                    new SellPercentage(NO_INSTRUMENT, new Percentage(sealedClassSellPercentageRequest.percentage));
            case SealedClassSellValueRequest sealedClassSellValueRequest ->
                    new SellValue(NO_INSTRUMENT, new Shares(sealedClassSellValueRequest.value));
        };
    }

    public static SealedClassSellBitcoinResponse convertToSealedClassResponse(SellInstrument response) {
        return switch (response) {
            case SellAmount sellAmount -> new SealedClassAmountResponse(convertModel(sellAmount.amount()));
            case SellPercentage sellPercentage -> new SealedClassValueResponse(sellPercentage.percentage().value());
            case SellValue sellValue -> new SealedClassValueResponse(sellValue.shares().value());
        };
    }

    private static Amount convertModel(api.model.Amount amount) {
        return new Amount(Currency.getInstance(amount.currency()), amount.value());
    }

    private static api.model.Amount convertModel(Amount amount) {
        return new api.model.Amount(amount.value(), amount.currency().getCurrencyCode());
    }

}
