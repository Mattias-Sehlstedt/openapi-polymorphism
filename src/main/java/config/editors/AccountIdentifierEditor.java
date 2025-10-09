package config.editors;

import api.model.query.AccountIdentifier;
import io.micrometer.common.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class AccountIdentifierEditor extends PropertyEditorSupport {

    public AccountIdentifierEditor() {
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            setValue(new AccountIdentifier(text));
        }
    }
}
