package api.model.jsonTypeAndOneOf;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(description = "Child 1")
public final class Child1 extends Parent {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    String child1Field;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "Child1")
    @NotNull
    public String getType() {
        return type;
    }
}
