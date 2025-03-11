package api.model.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class Sort {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "type")
    private final String type;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "direction")
    private final String direction;

    @JsonCreator
    Sort(
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "type")
            @JsonProperty("type")
            String type,
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "direction")
            @JsonProperty("direction")
            String direction
    ) {
        this.type = type;
        this.direction = direction;
    }

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "type")
    public String type() {
        return type;
    }

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "direction")
    public String direction() {
        return direction;
    }

}
