package api.controller.model.corporate.actions.v1;

import api.model.Amount;

public record InstrumentAllocation(
        Instrument instrument,
        Amount price,
        SharesRatio sharesRatio
) {
}
