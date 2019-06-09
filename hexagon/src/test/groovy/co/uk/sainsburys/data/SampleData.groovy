package co.uk.sainsburys.data

import co.uk.sainsburys.application.ProductsResult
import co.uk.sainsburys.application.ProductsResultFactory
import co.uk.sainsburys.domain.Product
import groovy.transform.CompileStatic

@CompileStatic
class SampleData {

    static Product make(Map<String, Object> props = [:]) {
        props = SAMPLE_PRODUCT_PROPERTIES + props
        return Product.builder()
                .title(props.title as String)
                .build();
    }

    static Map SAMPLE_PRODUCT_PROPERTIES = [
            title: "Blackcurrent's 100g"
    ]

    static ProductsResult makeResult(List<Product> products, Number gross, Number vat) {
        return ProductsResultFactory.getResult(products, gross, vat)
    }

    static ProductsResult makeResult() {
        return ProductsResultFactory.getResult([make()], 0, 0)
    }

    static ProductsResult emptyResult() {
        return ProductsResultFactory.getResult([], 0, 0)
    }

}
