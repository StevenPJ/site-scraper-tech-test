package co.uk.sainsburys.repository.creator

import co.uk.sainsburys.data.SampleData
import co.uk.sainsburys.domain.Product
import co.uk.sainsburys.driven.data.ProductCreator
import spock.lang.Specification
import spock.lang.Unroll


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

    @Unroll
    def "should parse #calories from #calString"() {
        when:
            Product product = creator.create(
                    "title",
                    "description",
                    calString,
                    "2.50")
        then:
            product.getCalories() == calories
        where:
            calString   | calories
            "200kcal"   | 200
            "10c"       | 10
            "c20"       | 20
            null        | null
    }

    void assertProductContains(Product product, String title, String description, Integer calories, Number amount) {
        assert product.getTitle() == title
        assert product.getDescription() == description
        assert product.getCalories() == calories
        assert product.getPrice() == SampleData.money(amount)
    }

}