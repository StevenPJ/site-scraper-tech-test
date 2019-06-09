package co.uk.sainsburys;


import co.uk.sainsburys.application.ProductsService;
import co.uk.sainsburys.driven.data.Dao;
import co.uk.sainsburys.driven.data.ProductCreator;
import co.uk.sainsburys.driven.data.ProductRepository;
import co.uk.sainsburys.driven.presenter.Presenter;
import co.uk.sainsburys.driven.total.GrossTotalStrategy;
import co.uk.sainsburys.driven.total.TotalStrategy;
import co.uk.sainsburys.driver.GetProducts;
import co.uk.sainsburys.repository.ProductDetails;
import co.uk.sainsburys.repository.ScrapedProductRepository;
import co.uk.sainsburys.repository.creator.ScrapedProductCreator;
import co.uk.sainsburys.repository.dao.ProductDetailsDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MainConfiguration {

    @Bean
    public Presenter presenter() {
       return new ConsoleWriter();
    }

    @Bean
    public ProductCreator productCreator() {
        return new ScrapedProductCreator();
    }

    @Bean
    public Dao<List<ProductDetails>> productDetailsDao() {
        return new ProductDetailsDao();
    }


    @Bean
    public ProductRepository productRepository(Dao<List<ProductDetails>> dao, ProductCreator creator) {
        return new ScrapedProductRepository(dao, creator);
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
