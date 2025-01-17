package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import polymorphism.api.DiscriminatedRequestBodyApi;
import polymorphism.api.FlatRequestBodyApi;
import polymorphism.api.JsonTypeRequestBodyApi;
import polymorphism.invoker.ApiClient;

@Configuration
public class ApiConfiguration {

    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }

    @Bean
    public FlatRequestBodyApi flatRequestBodyApi(ApiClient apiClient) {
        return new FlatRequestBodyApi(apiClient);
    }

    @Bean
    public JsonTypeRequestBodyApi jsonTypeRequestBodyApi(ApiClient apiClient) {
        return new JsonTypeRequestBodyApi(apiClient);
    }

    @Bean
    public DiscriminatedRequestBodyApi discriminatedRequestBodyApi(ApiClient apiClient) {
        return new DiscriminatedRequestBodyApi(apiClient);
    }

}
