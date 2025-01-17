package api.model.jsonTypeAndOneOf;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "Child 2")
public final class Child2 extends Parent {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    String child2Field;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Child2")
    @NotNull
    public String getType() {
        return type;
    }
}
