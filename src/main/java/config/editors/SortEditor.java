package config.editors;

import api.model.query.Sort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class SortEditor extends PropertyEditorSupport {

    private ObjectMapper objectMapper;

    public SortEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            Sort sort;
            try {
                sort = objectMapper.readValue(text, Sort.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(sort);
        }
    }
}
