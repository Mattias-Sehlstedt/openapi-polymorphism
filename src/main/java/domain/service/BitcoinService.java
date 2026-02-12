package domain.service;

import adapter.SellBitcoinAdapter;
import domain.model.SellInstrument;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class BitcoinService {

    SellBitcoinAdapter adapter;

    public Mono<SellInstrument> sellInstrument(SellInstrument sellInstrument) {
        return adapter.sellBitcoinExplicit(sellInstrument);
    }

}
