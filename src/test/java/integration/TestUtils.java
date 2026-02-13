package integration;

import com.github.tomakehurst.wiremock.client.MappingBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class TestUtils {

    private static final String X_IDEMPOTENCY = "x-idempotency";
    private static final String X_IDEMPOTENCY_VALUE = "idempotency";
    private static final String X_TRACE = "x-trace";
    private static final String X_TRACE_VALUE = "trace";
    private static final String X_AUTHENTICATION = "x-authentication";
    private static final String X_AUTHENTICATION_VALUE = "authentication";
    private static final String SELL_BITCOIN_URL = "/root/json-type/explicit/sell-bitcoin";

    public static MappingBuilder sellBitcoinReturns() {
        return bitcoinApplicationBase(post(urlPathMatching(SELL_BITCOIN_URL)));
    }

    private static MappingBuilder bitcoinApplicationBase(MappingBuilder builder) {
        return builder
                .withHeader(X_IDEMPOTENCY, equalTo(X_IDEMPOTENCY_VALUE))
                .withHeader(X_TRACE, equalTo(X_TRACE_VALUE))
                .withHeader(X_AUTHENTICATION, equalTo(X_AUTHENTICATION_VALUE));
    }

}
