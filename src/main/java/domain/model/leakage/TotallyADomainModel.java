package domain.model.leakage;

public record TotallyADomainModel(String data, InternalData internalData) {

    public record InternalData(String data, Exception exception) {

    }

}
