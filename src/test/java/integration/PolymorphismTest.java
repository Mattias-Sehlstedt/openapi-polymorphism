package integration;

import api.model.Amount;
import api.model.jsonType.explicit.type.request.ExplicitTypeAmountRequest;
import api.model.jsonType.explicit.type.request.ExplicitTypeSellBitcoinRequest;
import api.model.jsonType.response.SealedInterfaceAmountResponse;
import api.model.jsonType.response.SealedInterfacePercentageResponse;
import api.model.jsonType.response.SealedInterfaceSellBitcoinResponse;
import api.model.jsonType.response.SealedInterfaceValueResponse;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import polymorphism.Main;

import java.math.BigDecimal;
import java.net.URI;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Main.class,
        properties = {"spring.profiles.active=client"}
)
@WireMockTest(httpPort = 8080)
public class PolymorphismTest {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_JSON = "application/json";
    private static final String BASE_PATH = "http://localhost:";

    private static final String CURRENCY_VALUE = "SEK";
    private static final BigDecimal EXPECTED_VALUE = BigDecimal.valueOf(10);
    private static final Amount EXPECTED_AMOUNT = new Amount(EXPECTED_VALUE, CURRENCY_VALUE);
    private static final BigDecimal EXPECTED_PERCENTAGE = BigDecimal.valueOf(96);
    private static final SealedInterfaceSellBitcoinResponse EXPECTED_AMOUNT_RESPONSE =
            new SealedInterfaceAmountResponse(EXPECTED_AMOUNT);
    private static final SealedInterfaceSellBitcoinResponse EXPECTED_PERCENTAGE_RESPONSE =
            new SealedInterfacePercentageResponse(EXPECTED_PERCENTAGE);
    private static final SealedInterfaceSellBitcoinResponse EXPECTED_VALUE_RESPONSE =
            new SealedInterfaceValueResponse(EXPECTED_VALUE);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @MethodSource("get_json_type_response")
    void get_json_type_response(SealedInterfaceSellBitcoinResponse expectedResponse, String responseBody) {
        given_endpoint_returns(responseBody);

        SealedInterfaceSellBitcoinResponse response = when_calling_endpoint();

        assertEquals(expectedResponse, response);
    }

    private void given_endpoint_returns(String responseBody) {
        stubFor(post(urlMatching("/json-type/explicit/sell-bitcoin"))
                .willReturn(created().withHeader(CONTENT_TYPE, CONTENT_JSON)
                        .withBody(responseBody))
        );
    }

    private SealedInterfaceSellBitcoinResponse when_calling_endpoint() {
        ExplicitTypeSellBitcoinRequest request = new ExplicitTypeAmountRequest(
                new Amount(BigDecimal.valueOf(10), "SEK")
        );
        return restTemplate
                .exchange(new RequestEntity<>(request, HttpMethod.POST, URI.create(BASE_PATH + port + "/json-type/explicit/sell-bitcoin")),
                        SealedInterfaceSellBitcoinResponse.class)
                .getBody();
    }

    private static Stream<Arguments> get_json_type_response() {
        return Stream.of(
                Arguments.of(EXPECTED_AMOUNT_RESPONSE, """
                        {
                            type: AMOUNT,
                            "amount" : {
                                "value": 10,
                                "currency" : "SEK"
                            }
                        }"""),
                Arguments.of(EXPECTED_PERCENTAGE_RESPONSE, """
                        {
                            type: PERCENTAGE,
                            "percentage" : 69
                        }"""),
                Arguments.of(EXPECTED_VALUE_RESPONSE, """
                        {
                            type: VALUE,
                            "value" : 10
                        }""")
        );
    }
}
