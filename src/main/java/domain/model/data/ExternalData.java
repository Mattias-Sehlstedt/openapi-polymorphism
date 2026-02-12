package domain.model.data;

import domain.model.Amount;
import domain.model.reference.ExternalReference;

public record ExternalData(
        ExternalReference reference,
        Amount amount
) {
}
