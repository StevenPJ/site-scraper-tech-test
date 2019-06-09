package co.uk.sainsburys.application

import co.uk.sainsburys.domain.Product
import co.uk.sainsburys.driven.data.ProductRepository
import co.uk.sainsburys.driven.presenter.Presenter
import co.uk.sainsburys.driver.GetProducts
import spock.lang.Specification


class GetProductsSpec extends Specification {

    String link = "link"
    List<Product> products = sampleProducts()

    Presenter presenter
    ProductRepository productRepository
    GetProducts getProducts;

    def setup() {
        presenter = Mock()
        productRepository = Mock()
        getProducts = new ProductsService(presenter, productRepository)
    }

    def "should show a result with found products"() {
        given:
            productRepository.search(link) >> products
        when:
            getProducts.fromPage(link);
        then:
            1 * presenter.show(ProductsResultFactory.getResult(products))
    }

    List<Product> sampleProducts() {
        return []
    }
}