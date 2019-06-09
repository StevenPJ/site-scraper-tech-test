package co.uk.sainsburys.repository

import co.uk.sainsburys.domain.Product
import co.uk.sainsburys.driven.data.Dao
import co.uk.sainsburys.driven.data.ProductCreator
import co.uk.sainsburys.driven.data.ProductRepository
import co.uk.sainsburys.repository.creator.ScrapedProductCreator
import spock.lang.Specification


class ProductRepositorySpec extends Specification {

    String link = "link"
    ProductRepository repo
    Dao<List<ProductDetails>> dao
    ProductCreator creator;

    def setup() {
        dao = Mock()
        creator = new ScrapedProductCreator();
        repo = new ScrapedProductRepository(dao, creator);
    }
    def "should create products"() {
        given: "dao finds 2 product details from link"
            dao.extractFrom(link) >> [SampleProductDetails.make(), SampleProductDetails.make()]
        when: "repo searches products"
            List<Product> products = repo.search(link)
        then: "repo returns 2 products"
            products.size() == 2
    }

}