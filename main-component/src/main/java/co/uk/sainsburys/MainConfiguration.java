package co.uk.sainsburys;


import co.uk.sainsburys.application.ProductsService;
import co.uk.sainsburys.driven.data.ProductRepository;
import co.uk.sainsburys.driven.presenter.Presenter;
import co.uk.sainsburys.driver.GetProducts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

    @Bean
    public Presenter presenter() {
        throw new UnsupportedOperationException();
    }

    @Bean
    public ProductRepository productRepository() {
        throw new UnsupportedOperationException();
    }

    @Bean
    public GetProducts getProducts(Presenter presenter, ProductRepository productRepository) {
        return new ProductsService(presenter, productRepository);
    }

    @Bean
    public ConsoleClient consoleClient(GetProducts getProducts, @Value("${page-url}") String pageLink) {
        return new ConsoleClient(getProducts, pageLink);
    }

}
