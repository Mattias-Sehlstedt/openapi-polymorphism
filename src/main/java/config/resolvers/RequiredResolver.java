package config.resolvers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;

public class RequiredResolver extends ModelResolver {

    public RequiredResolver(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    protected io.swagger.v3.oas.annotations.media.Schema.RequiredMode resolveRequiredMode(io.swagger.v3.oas.annotations.media.Schema schema) {
        if (schema != null && !schema.requiredMode().equals(io.swagger.v3.oas.annotations.media.Schema.RequiredMode.AUTO)) {
            return schema.requiredMode();
        } else if (schema != null && schema.required()) {
            return io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
        }
        return Schema.RequiredMode.REQUIRED;
    }

    @Override
    protected boolean addRequiredItem(io.swagger.v3.oas.models.media.Schema model, String propName) {
        if (model == null || propName == null || StringUtils.isBlank(propName)) {
            return false;
        }
        if (model.getRequired() == null || model.getRequired().isEmpty()) {
            model.addRequiredItem(propName);
        }
        if (model.getRequired().stream().noneMatch(propName::equals)) {
            model.addRequiredItem(propName);
        }
        return true;
    }
}
