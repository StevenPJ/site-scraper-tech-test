package co.uk.sainsburys;


import co.uk.sainsburys.application.ProductsService;
import co.uk.sainsburys.driver.GetProducts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

    @Bean
    public GetProducts getProducts() {
        return new ProductsService();
    }
}
