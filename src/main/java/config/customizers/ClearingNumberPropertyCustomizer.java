package config.customizers;

import api.annotation.ClearingNumber;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.PropertyCustomizer;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ClearingNumberPropertyCustomizer implements PropertyCustomizer {

    private static final String CLEARING_NUMBER_VENDOR_EXTENSION = "x-clearing-number";
    private static final String CLEARING_NUMBER_PATTERN = "^\\d{3}$";

    @Override
    public Schema customize(Schema property, AnnotatedType type) {
        Annotation[] ctxAnnotations = type.getCtxAnnotations();
        if (ctxAnnotations == null) {
            return property;
        }

        Optional<ClearingNumber> propertyAnnotation = Stream.of(ctxAnnotations)
                .filter(ClearingNumber.class::isInstance)
                .findFirst()
                .map(ClearingNumber.class::cast);

        if (propertyAnnotation.isPresent()) {
            property.setPattern(CLEARING_NUMBER_PATTERN);
            property.addExtension(CLEARING_NUMBER_VENDOR_EXTENSION, true);
        }

        return property;
    }
}
