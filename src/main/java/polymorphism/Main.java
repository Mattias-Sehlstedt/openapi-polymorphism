package polymorphism;

import adapter.SellBitcoinAdapter;
import config.SellBitcoinComponent;
import config.resolvers.RequiredResolver;
import controller.Controller;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.util.Json;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import polymorphism.api.DiscriminatedRequestBodyApi;
import polymorphism.api.FlatRequestBodyApi;
import polymorphism.api.JsonTypeRequestBodyApi;

@SpringBootApplication(scanBasePackageClasses = {
        Controller.class,
        SellBitcoinComponent.class,
        SellBitcoinAdapter.class,
        FlatRequestBodyApi.class,
        JsonTypeRequestBodyApi.class,
        DiscriminatedRequestBodyApi.class
})
public class Main extends SpringBootServletInitializer {

    static {
        ModelConverters.getInstance().addConverter(new RequiredResolver(Json.mapper()));
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}