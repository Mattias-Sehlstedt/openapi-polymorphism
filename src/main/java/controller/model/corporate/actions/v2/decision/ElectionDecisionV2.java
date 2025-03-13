package controller.model.corporate.actions.v2.decision;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(name = "ElectionDecisionV2FixedElection", value = ElectionDecisionV2FixedElection.class),
                @JsonSubTypes.Type(name = "ElectionDecisionV2Oversubscription", value = ElectionDecisionV2Oversubscription.class),
                @JsonSubTypes.Type(name = "ElectionDecisionV2Quantity", value = ElectionDecisionV2Quantity.class),
                @JsonSubTypes.Type(name = "ElectionDecisionV2Payout", value = ElectionDecisionV2Payout.class)
        }
)
public sealed interface ElectionDecisionV2 permits ElectionDecisionV2FixedElection, ElectionDecisionV2Oversubscription, ElectionDecisionV2Payout, ElectionDecisionV2Quantity {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    String type();

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    int electionId();
}
