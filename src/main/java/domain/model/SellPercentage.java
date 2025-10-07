package domain.model;

public record SellPercentage(
        Instrument instrument,
        Percentage percentage
) implements SellInstrument {
}
