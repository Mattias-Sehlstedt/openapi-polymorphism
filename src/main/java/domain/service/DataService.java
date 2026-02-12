package domain.service;

import adapter.ReferenceAdapter;
import adapter.RequestAdapter;
import domain.model.Id;
import domain.model.data.ExternalData;
import domain.model.data.InternalData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class DataService {

    ReferenceAdapter referenceAdapter;
    RequestAdapter requestAdapter;

    Mono<Id> placeRequest(InternalData data) {
        return referenceAdapter.translate(data.reference())
                .map(externalReference -> new ExternalData(externalReference, data.amount()))
                .flatMap(requestAdapter::placeRequest);
    }

    Mono<InternalData> getRequest(Id id) {
        return requestAdapter.getRequest(id)
                .flatMap(externalData -> referenceAdapter.translate(externalData.reference())
                        .map(internalReference -> new InternalData(internalReference, externalData.amount())));
    }

}
