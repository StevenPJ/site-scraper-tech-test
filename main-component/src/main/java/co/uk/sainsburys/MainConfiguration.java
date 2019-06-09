package co.uk.sainsburys;


import co.uk.sainsburys.application.ProductsService;
import co.uk.sainsburys.driven.data.ProductRepository;
import co.uk.sainsburys.driven.presenter.Presenter;
import co.uk.sainsburys.driven.total.GrossTotalStrategy;
import co.uk.sainsburys.driven.total.TotalStrategy;
import co.uk.sainsburys.driver.GetProducts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfiguration {

    @Bean
    public Presenter presenter() {
       return new ConsoleWriter();
    }

    @Bean
    public ProductRepository productRepository() {
        throw new UnsupportedOperationException();
    }

    @Bean
    public TotalStrategy totalStrategy(@Value("${vat.rate.STANDARD}") Integer standardRate) {
        return new GrossTotalStrategy(standardRate);
    }

    @Bean
    public GetProducts getProducts(Presenter presenter, ProductRepository productRepository, TotalStrategy totalStrategy) {
        return new ProductsService(presenter, productRepository, totalStrategy);
    }

    @Bean
    public ConsoleClient consoleClient(GetProducts getProducts, @Value("${page-url}") String pageLink) {
        return new ConsoleClient(getProducts, pageLink);
    }

}
