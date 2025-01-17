package api.model.jsonTypeAndOneOf;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
//@Schema(
//        discriminatorProperty = "type",
//        discriminatorMapping = {
//                @DiscriminatorMapping(value = "Child1", schema = Child1.class),
//                @DiscriminatorMapping(value = "Child2", schema = Child2.class)
//        },
//        oneOf = {
//                Child1.class,
//                Child2.class
//        }
//)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = Child1.class, name = "Child1"),
                @JsonSubTypes.Type(value = Child2.class, name = "Child2"),
        }
)
public abstract sealed class Parent permits Child1, Child2 {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    protected String type;
}
