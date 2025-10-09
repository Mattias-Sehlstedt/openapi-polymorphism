package api.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@ToString
@Schema(example = "123-456789", type = "string")
public class AccountIdentifier {

    public String identifier;

    public AccountIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
