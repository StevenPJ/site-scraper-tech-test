package co.uk.sainsburys.application;

import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.driven.data.ProductRepository;
import co.uk.sainsburys.driven.presenter.Presenter;
import co.uk.sainsburys.driver.GetProducts;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductsService implements GetProducts {

    private Presenter presenter;
    private ProductRepository productRepository;

    @Override
    public void fromPage(String pageLink) {
        List<Product> products = productRepository.search(pageLink);
        ProductsResult result = ProductsResultFactory.getResult(products);
        presenter.show(result);
    }
}
