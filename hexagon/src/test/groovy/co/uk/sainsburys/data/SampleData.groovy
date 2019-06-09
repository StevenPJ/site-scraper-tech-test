package co.uk.sainsburys.data

import co.uk.sainsburys.application.ProductsResult
import co.uk.sainsburys.application.ProductsResultFactory
import co.uk.sainsburys.domain.Money
import co.uk.sainsburys.domain.Product
import groovy.transform.CompileStatic

@CompileStatic
class SampleData {

    static Product make(Map<String, Object> props = [:]) {
        props = SAMPLE_PRODUCT_PROPERTIES + props
        return Product.builder()
                .title(props.title as String)
                .calories(props.calories as Integer)
                .price(new Money(props.price as Number))
                .description(props.description as String)
                .build();
    }

    static Map SAMPLE_PRODUCT_PROPERTIES = [
            title: "Blackcurrent's 100g",
            calories: 22,
            price: 1.5,
            description: "by Sainsbury's ltd"
    ]

    static Map SAMPLE_PRODUCT_JSON_PROPERTIES = [
            title: "Blackcurrent's 100g",
            kcal_per_100g: 22,
            unit_price: 1.5,
            description: "by Sainsbury's ltd"
    ]

    static ProductsResult makeResult(List<Product> products, Number gross, Number vat) {
        return ProductsResultFactory.getResult(products, money(gross), money(vat))
    }

    static ProductsResult makeResult(List<Product> products) {
        return ProductsResultFactory.getResult(products, money(0), money(0))
    }

    static ProductsResult makeResult() {
        return ProductsResultFactory.getResult([make()], money(0), money(0))
    }

    static ProductsResult emptyResult() {
        return ProductsResultFactory.getResult([], money(0), money(0))
    }

    static Money money(Number amount) {
        return new Money(amount)
    }

}
