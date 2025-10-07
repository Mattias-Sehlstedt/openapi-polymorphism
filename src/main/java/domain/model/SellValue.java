package domain.model;

public record SellValue(
        Instrument instrument,
        Shares shares
) implements SellInstrument {
}
