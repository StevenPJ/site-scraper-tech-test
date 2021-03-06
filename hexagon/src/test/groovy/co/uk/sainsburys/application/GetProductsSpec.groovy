package co.uk.sainsburys.application

import co.uk.sainsburys.data.SampleData
import co.uk.sainsburys.domain.Product
import co.uk.sainsburys.domain.exception.InvalidMoneyOperationException
import co.uk.sainsburys.domain.exception.PageLoadException
import co.uk.sainsburys.driven.data.ProductRepository
import co.uk.sainsburys.driven.presenter.Presenter
import co.uk.sainsburys.driven.total.GrossTotalStrategy
import co.uk.sainsburys.driven.total.TotalStrategy
import co.uk.sainsburys.driver.GetProducts
import spock.lang.Specification


class GetProductsSpec extends Specification {

    String link = "link"

    Presenter presenter
    ProductRepository productRepository
    TotalStrategy totalStrategy
    GetProducts getProducts;

    def setup() {
        presenter = Mock()
        productRepository = Mock()
        totalStrategy = new GrossTotalStrategy(20)
        getProducts = new ProductsService(presenter, productRepository, totalStrategy)
    }

    def "should show a result with found products"() {
        given:
            List<Product> products = [SampleData.make()]
            productRepository.search(link) >> products
        when:
            getProducts.fromPage(link);
        then:
            1 * presenter.show(_ as ProductsResult)
    }

    def "should show total gross of £5 and vat of £0.83 when one £5 products found"() {
        given:
            Product product = SampleData.make(price: 5)
            productRepository.search(link) >> [product]
        when:
            getProducts.fromPage(link);
        then:
            1 * presenter.show(SampleData.makeResult(product: product, gross: 5, vat: 0.83))
    }

    def "should show error message when InvalidMoneyOperation"() {
        given:
            productRepository.search(link) >> {throw new InvalidMoneyOperationException("message")}
        when:
            getProducts.fromPage(link);
        then:
            1 * presenter.showErrorMessage("message")
    }

    def "should show error message when PageLoadException"() {
        given:
            productRepository.search(link) >> {throw new PageLoadException("reason", "link", null)}
        when:
            getProducts.fromPage(link);
        then:
            1 * presenter.showErrorMessage("Unable to load page link Reason: reason")
    }

}