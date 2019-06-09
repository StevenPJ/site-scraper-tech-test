package co.uk.sainsburys.domain

import co.uk.sainsburys.data.SampleData
import co.uk.sainsburys.driven.total.GrossTotalStrategy
import co.uk.sainsburys.driven.total.TotalStrategy
import spock.lang.Specification
import spock.lang.Unroll


class TotalStrategySpec extends Specification {

    TotalStrategy totalStrategy

    def setup() {
        totalStrategy = new GrossTotalStrategy(20)
    }

    def "should have £1.67 vat on two £5 products"() {
        given: "products with gross"
            def products = [ SampleData.make(price: 5), SampleData.make(price: 5) ]
        expect: "VAT to be 1.67"
            totalStrategy.calculateTotalVat(products) == SampleData.money(1.67)
    }

    def "should have £10.05 gross with one £7.5 product and one £2.55 product"() {
        given: "products with gross and standard rate of VAT applied"
            def products = [ SampleData.make(price: 7.5), SampleData.make(price: 2.55) ]
        expect: "Gross to be 10"
            totalStrategy.calculateTotalGross(products) == SampleData.money(10.05)
    }

    def "should return 0 when no products"() {
        expect:
            totalStrategy.calculateTotalVat([]) == SampleData.money(0)
            totalStrategy.calculateTotalGross([]) == SampleData.money(0)
    }

    def "should return 0 when all products has no price"() {
        expect:
            totalStrategy.calculateTotalVat([SampleData.make(price: 0)]) == SampleData.money(0)
            totalStrategy.calculateTotalGross([SampleData.make(price: 0)]) == SampleData.money(0)
    }

    @Unroll
    def "should calculate £#vat vat from £#gross gross"() {
        given: "product with gross"
            def product = SampleData.make(price: gross)
        expect: "VAT to be #vat"
            totalStrategy.calculateTotalVat([product]) == SampleData.money(vat);
        where:
            gross   | vat
            5       | 0.83
            1       | 0.17
            0       | 0
    }

    def "should have £0.83 vat on two £5 products with standard rate applied to only one"() {
        given: "products with gross and standard rate of VAT applied"
            def products = [ SampleData.make(price: 5),
                             SampleData.make(price: 5, rate: null)
            ]
        expect: "VAT to be 0.83"
            totalStrategy.calculateTotalVat(products) == SampleData.money(0.83);
    }

    def "should have £1.67 vat on two £5 products with standard rate applied"() {
        given: "products with gross and standard rate of VAT applied"
            def products = [ SampleData.make(price: 5),
                             SampleData.make(price: 5)
            ]
        expect: "VAT to be 1.67"
            totalStrategy.calculateTotalVat(products) == SampleData.money(1.67);
    }

    def "should calculate gross if vatrate null"() {
        given: "product with gross"
            def product = SampleData.make(price: 5, rate: null)
        expect: "VAT to be #vat"
            totalStrategy.calculateTotalGross([product]) == SampleData.money(5);
    }

    def "standard rate amount should be configurable"() {
        given: "configured strategy with standard rate"
            def strategy = new GrossTotalStrategy(standardRate)
        and: "product with £5 price and standard rate applied"
            def product = SampleData.make(price: 5)
        expect: "VAT to be #vat"
            strategy.calculateTotalVat([product]) == SampleData.money(vat);
        where:
            standardRate    | vat
            20              | 0.83
            0               | 0
    }


}