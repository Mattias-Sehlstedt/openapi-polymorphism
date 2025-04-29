package config.editors;

import api.model.query.SortList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class SortListEditor extends PropertyEditorSupport {

    private ObjectMapper objectMapper;

    public SortListEditor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            SortList sort = new SortList();
            try {
                sort.sortList = objectMapper.readValue(text, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(sort);
        }
    }
}
