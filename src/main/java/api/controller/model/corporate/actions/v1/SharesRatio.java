package api.controller.model.corporate.actions.v1;

public record SharesRatio(
        Instrument newInstrument,
        Instrument oldInstrument,
        Ratio ratio
) {
}
