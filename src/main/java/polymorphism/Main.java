package polymorphism;

import adapter.SellBitcoinAdapter;
import config.SellBitcoinComponent;
import controller.Controller;
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

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}