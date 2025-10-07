package domain.service;

import adapter.SellBitcoinAdapter;
import domain.model.SellInstrument;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class DataService {

    SellBitcoinAdapter adapter;

    public Mono<SellInstrument> sellInstrument(SellInstrument sellInstrument) {
        return adapter.sellBitcoinExplicit(sellInstrument);
    }

}
