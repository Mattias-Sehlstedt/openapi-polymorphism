package adapter;

import domain.model.reference.ExternalReference;
import domain.model.reference.InternalReference;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReferenceAdapter {

    public Mono<ExternalReference> translate(InternalReference reference) {
        return Mono.just(new ExternalReference(reference.value()));
    }

    public Mono<InternalReference> translate(ExternalReference reference) {
        return Mono.just(new InternalReference(reference.value()));
    }

}
