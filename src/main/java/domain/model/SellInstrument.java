package domain.model;

public sealed interface SellInstrument permits SellAmount, SellPercentage, SellValue {
    Instrument instrument();
}
