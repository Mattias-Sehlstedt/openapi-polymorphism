package integration;

import api.model.Amount;
import api.model.jsonType.explicit.type.request.ExplicitTypeAmountRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypeSellBitcoinRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import polymorphism.model.AmountDTO;
import polymorphism.model.ExplicitTypeAmountRequestDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Playground {

    @Test
    void test() {
        ResolvedSchema resolvedSchema = ModelConverters.getInstance(true)
                .resolveAsResolvedSchema(
                        new AnnotatedType(Parent.class).resolveAsRef(false));
        var test = "hej";
    }

    @Test
    void serializationTest() throws JsonProcessingException {
        ExplicitTypeSellBitcoinRequest request = new ExplicitTypeAmountRequest(
                new Amount(BigDecimal.valueOf(10), "SEK")
        );
        ObjectMapper objectMapper = new ObjectMapper();
        String object = objectMapper.writeValueAsString(request);
        assertEquals("{\"type\":\"AMOUNT\",\"amount\":{\"value\":10,\"currency\":\"SEK\"}}", object);
    }

    @Schema(
            discriminatorProperty = "type",
            discriminatorMapping = {
                    @DiscriminatorMapping(value = "Child1", schema = Child1.class),
                    @DiscriminatorMapping(value = "Child2", schema = Child2.class)
            }
    )
    public interface Discriminator {

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        String getType();
    }

    @Schema(implementation = Discriminator.class)
    public sealed interface Parent extends Discriminator permits Child1, Child2 {
    }

    public final class Child1 implements Parent {

        @Override
        public String getType() {
            return "";
        }
    }

    public final class Child2 implements Parent {

        @Override
        public String getType() {
            return "";
        }
    }

    @Test
    void test2() {
        assertDoesNotThrow(() -> {
            ExplicitTypeAmountRequestDTO data = new ExplicitTypeAmountRequestDTO()
                    .type(null)
                    .amount(new AmountDTO()
                            .currency("SEK")
                            .value(BigDecimal.valueOf(10)));
        });
    }

    @Test
    void test3() {
        assertDoesNotThrow(() -> {
            ExplicitTypeAmountRequestDTO data = ExplicitTypeAmountRequestDTO
                    .builder()
                    .amount(new AmountDTO()
                            .currency("SEK")
                            .value(BigDecimal.valueOf(10)))
                    .build();
        });
    }
}
