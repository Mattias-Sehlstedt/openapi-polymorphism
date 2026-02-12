package domain.model.data;

import domain.model.Amount;
import domain.model.reference.InternalReference;

public record InternalData(
        InternalReference reference,
        Amount amount
) {
}
