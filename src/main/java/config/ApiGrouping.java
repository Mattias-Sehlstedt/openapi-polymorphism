package config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGrouping {

    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("main-functionality")
                .pathsToMatch("/root/**")
                .pathsToExclude("/root/query/**")
                .build();
    }

    @Bean
    public GroupedOpenApi queryTestingApi() {
        return GroupedOpenApi.builder()
                .group("query-testing")
                .pathsToMatch("/root/query/**")
                .build();
    }

}
