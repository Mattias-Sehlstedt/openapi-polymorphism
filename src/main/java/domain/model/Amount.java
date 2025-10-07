package domain.model;

import java.math.BigDecimal;
import java.util.Currency;

public record Amount(
        Currency currency,
        BigDecimal value
) {
}
