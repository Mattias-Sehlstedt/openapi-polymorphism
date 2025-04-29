package api.model.jsonTypeAndOneOf;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(implementation = ParentDiscriminator.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = Child1.class, names = {"Child1", "CHILD_1"}),
                @JsonSubTypes.Type(value = Child2.class, names = {"Child2", "CHILD_2"}),
        }
)
public abstract sealed class Parent implements ParentDiscriminator permits Child1, Child2 {

    protected EnumType type;

    protected String parentField;

    abstract String setName();
}
