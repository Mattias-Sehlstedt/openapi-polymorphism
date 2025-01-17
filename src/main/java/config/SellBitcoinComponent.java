package config;

import api.model.Amount;
import api.model.TransferType;
import api.model.discriminator.request.SealedClassSellAmountRequest;
import api.model.discriminator.request.SealedClassSellBitcoinRequest;
import api.model.discriminator.request.SealedClassSellPercentageRequest;
import api.model.discriminator.request.SealedClassSellValueRequest;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class SellBitcoinComponent {

    public static class Deserializer extends JsonDeserializer<SealedClassSellBitcoinRequest> {

        @Override
        public SealedClassSellBitcoinRequest deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
            ObjectCodec codec = jsonParser.getCodec();
            JsonNode tree = codec.readTree(jsonParser);
            TransferType transferType = TransferType.valueOf(tree.get("transferType").textValue());
            return switch (transferType) {
                case PERCENTAGE -> new SealedClassSellPercentageRequest(tree.get("percentage").decimalValue());
                case AMOUNT -> new SealedClassSellAmountRequest(
                        new Amount(
                                tree.get("amount").get("value").decimalValue(),
                                tree.get("amount").get("currency").textValue())
                );
                case VALUE -> new SealedClassSellValueRequest(tree.get("value").decimalValue());
            };
        }
    }
}
