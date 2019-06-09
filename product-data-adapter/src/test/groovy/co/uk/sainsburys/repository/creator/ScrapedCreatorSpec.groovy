package co.uk.sainsburys.repository.creator

import co.uk.sainsburys.data.SampleData
import co.uk.sainsburys.domain.Product
import co.uk.sainsburys.driven.data.ProductCreator
import spock.lang.Specification


class ScrapedCreatorSpec extends Specification {
    ProductCreator creator

    def setup() {
        creator = new ScrapedProductCreator()
    }

    def "should create product with happy values"() {
        when:
        Product product = creator.create(
                "title",
                "description",
                "22",
                "2.50")
        then:
        assertProductContains(product,
                "title",
                "description",
                22,
                2.5)
    }

    void assertProductContains(Product product, String title, String description, Integer calories, Number amount) {
        assert product.getTitle() == title
        assert product.getDescription() == description
        assert product.getCalories() == calories
        assert product.getPrice() == SampleData.money(amount)
    }

}