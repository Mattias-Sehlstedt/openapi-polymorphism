package domain.model;

public record SellAmount(
        Instrument instrument,
        Amount amount
) implements SellInstrument {
}
