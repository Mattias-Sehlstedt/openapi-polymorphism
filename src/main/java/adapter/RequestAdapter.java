package adapter;

import domain.model.Amount;
import domain.model.Id;
import domain.model.data.ExternalData;
import domain.model.reference.ExternalReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Currency;

@Component
public class RequestAdapter {

    private static final String ID = "id";
    private static final Amount AMOUNT = new Amount(Currency.getInstance("SEK"), BigDecimal.valueOf(67));
    private static final String VALUE = "value";
    private static final ExternalReference EXTERNAL_REFERENCE = new ExternalReference(VALUE);
    private static final ExternalData DATA = new ExternalData(EXTERNAL_REFERENCE, AMOUNT);

    public Mono<Id> placeRequest(ExternalData data) {
        return Mono.just(new Id(ID));
    }

    public Mono<ExternalData> getRequest(Id id) {
        return Mono.just(DATA);
    }

}
