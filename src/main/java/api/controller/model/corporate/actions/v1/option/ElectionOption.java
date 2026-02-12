package api.controller.model.corporate.actions.v1.option;

import api.model.Amount;
import api.controller.model.corporate.actions.v1.InstrumentAllocation;

import java.math.BigDecimal;
import java.util.List;

public record ElectionOption(
        int electionId,
        boolean isDefaultOption,
        boolean isOversubscribe,
        boolean doNotParticipate,
        List<Amount> cashActions,
        Integer quantity,
        boolean allowsOverSubscription,
        BigDecimal maximumOverSubScriptionPercentage,
        List<InstrumentAllocation> instrumentAllocations
) {

}