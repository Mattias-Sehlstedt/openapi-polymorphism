package api.model.jsonTypeAndOneOf;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Schema(description = "Child2")
public final class Child2 extends Parent {

    private static final String NAME = "CHILD_2";

    String child2Field;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ChildField2")
    @NotNull
    public String getChild2Field() {
        return child2Field;
    }

    public void setChild2Field() {
    }

    public void setType(EnumType type) {
        this.type = type;
    }

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = NAME)
    @NotNull
    public EnumType getType() {
        return EnumType.CHILD_2;
    }


    @Override
    public void setParentField(String parentField) {
        this.parentField = parentField;
    }

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = NAME)
    @NotNull
    @Override
    public String getParentField() {
        return parentField;
    }

    @Override
    String setName() {
        return "";
    }
}
