package co.uk.sainsburys.repository.creator

import co.uk.sainsburys.data.SampleData
import co.uk.sainsburys.domain.Product
import co.uk.sainsburys.domain.VatRate
import co.uk.sainsburys.driven.data.ProductCreator
import spock.lang.Specification
import spock.lang.Unroll


class ScrapedCreatorSpec extends Specification {
    ProductCreator creator

    def setup() {
        creator = new ScrapedProductCreator(VatRate.STANDARD_RATE)
    }

    def "should create product with happy values"() {
        when:
            def product = creator.create(
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
            def product = creator.create(
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

    @Unroll
    def "should parse #price from #moneyString"() {
        when:
            def product = creator.create(
                    "title",
                    "description",
                    "22",
                    moneyString)
        then:
            product.getPrice() == SampleData.money(price)
        where:
            moneyString  | price
            "£2.5"       | 2.5
            "£1"         | 1
            "4.55"       | 4.55
            "£2.75/unit" | 2.75
            "0"          | 0
            "0.50"       | 0.5
            "0.5"        | 0.5
            null         | 0
    }

    def "should apply default rate to all products"() {
        when:
            def product = creator.create(
                "title",
                "description",
                "22",
                "1")
        then:
            product.getVatRate() == VatRate.STANDARD_RATE
    }

    def "should create product with null values"() {
        when:
        def product = creator.create(null, null, null, null)
        then:
        assertProductContains(product,
                null,
                null,
                null,
                0)
    }

    void assertProductContains(Product product, String title, String description, Integer calories, Number amount) {
        assert product.getTitle() == title
        assert product.getDescription() == description
        assert product.getCalories() == calories
        assert product.getPrice() == SampleData.money(amount)
    }

}