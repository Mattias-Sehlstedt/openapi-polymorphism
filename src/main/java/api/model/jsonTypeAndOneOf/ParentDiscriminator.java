package api.model.jsonTypeAndOneOf;

import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        discriminatorProperty = "type",
        discriminatorMapping = {
                @DiscriminatorMapping(value = "CHILD_1", schema = Child1.class),
                @DiscriminatorMapping(value = "CHILD_2", schema = Child2.class)
        },
        oneOf = {
                Child1.class,
                Child2.class
        }
)
public sealed interface ParentDiscriminator permits Parent {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    EnumType getType();

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "ParentFieldExample")
    void setParentField(String parentField);

    String getParentField();
}
