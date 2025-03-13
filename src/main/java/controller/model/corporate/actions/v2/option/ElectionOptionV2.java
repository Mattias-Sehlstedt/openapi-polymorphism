package controller.model.corporate.actions.v2.option;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(name = "ElectionOptionV2DoNotParticipate", value = ElectionOptionV2DoNotParticipate.class),
                @JsonSubTypes.Type(name = "ElectionOptionV2Cash", value = ElectionOptionV2Cash.class),
                @JsonSubTypes.Type(name = "ElectionOptionV2Instrument", value = ElectionOptionV2Instrument.class)
        }
)
public sealed interface ElectionOptionV2 permits ElectionOptionV2Cash, ElectionOptionV2DoNotParticipate, ElectionOptionV2Instrument, ElectionOptionV2RatioChange {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    String type();

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    int electionId();
}