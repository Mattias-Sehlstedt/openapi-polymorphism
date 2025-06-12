import org.junit.jupiter.api.Test;
import polymorphism.model.AmountDTO;
import polymorphism.model.ExplicitTypeAmountRequestDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlaygroundTest {

    private static final String CURRENCY_SEK = "SEK";
    private static final AmountDTO AMOUNT = new AmountDTO()
            .currency(CURRENCY_SEK)
            .value(BigDecimal.valueOf(10));

    @Test
    void WithoutBuilder() {
        assertDoesNotThrow(() -> {
            ExplicitTypeAmountRequestDTO data = new ExplicitTypeAmountRequestDTO()
                    .type(null)
                    .amount(AMOUNT);
        });
    }

    @Test
    void WithBuilder() {
        assertDoesNotThrow(() -> {
            ExplicitTypeAmountRequestDTO data = ExplicitTypeAmountRequestDTO
                    .builder()
                    .amount(AMOUNT)
                    .build();
        });
    }
}
