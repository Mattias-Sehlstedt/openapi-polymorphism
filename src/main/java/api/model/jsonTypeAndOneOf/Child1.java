package api.model.jsonTypeAndOneOf;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Schema(description = "Child 1")
public final class Child1 extends Parent {

    private static final String NAME = "CHILD_1";

    String child1Field;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ChildField1")
    @NotNull
    public String getChild1Field() {
        return child1Field;
    }

    public void setChild1Field() {
    }

    public void setType(EnumType type) {
        this.type = type;
    }

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = NAME)
    @NotNull
    public EnumType getType() {
        return EnumType.CHILD_1;
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
