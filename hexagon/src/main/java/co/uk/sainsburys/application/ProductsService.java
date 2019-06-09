package co.uk.sainsburys.application;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.domain.exception.InvalidMoneyOperationException;
import co.uk.sainsburys.driven.data.ProductRepository;
import co.uk.sainsburys.driven.presenter.Presenter;
import co.uk.sainsburys.driven.total.TotalStrategy;
import co.uk.sainsburys.driver.GetProducts;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductsService implements GetProducts {

    private Presenter presenter;
    private ProductRepository productRepository;
    private TotalStrategy totalStrategy;

    @Override
    public void fromPage(String pageLink) {
        try {
            List<Product> products = productRepository.search(pageLink);
            Money gross = totalStrategy.calculateTotalGross(products);
            Money vat = totalStrategy.calculateTotalVat(products);
            ProductsResult result = ProductsResultFactory.getResult(products, gross, vat);
            presenter.show(result);
        } catch (InvalidMoneyOperationException e) {
            presenter.showErrorMessage(e.getMessage());
        }
    }
}
