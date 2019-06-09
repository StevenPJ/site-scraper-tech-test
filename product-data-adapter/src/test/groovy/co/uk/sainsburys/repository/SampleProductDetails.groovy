package co.uk.sainsburys.repository


import groovy.transform.CompileStatic

@CompileStatic
class SampleProductDetails {

    static ProductDetails make(Map<String, Object> props = [:]) {
        props = SAMPLE_PRODUCT_DETAILS_PROPERTIES + props
        return ProductDetails.builder()
                .title(props.title as String)
                .calories(props.calories as String)
                .price(props.price as String)
                .description(props.description as String)
                .build();
    }
    static Map SAMPLE_PRODUCT_DETAILS_PROPERTIES = [
            title: "Blackcurrent's 100g",
            calories: "22kcal",
            price: "£1.50/unit",
            description: "by Sainsbury's ltd"
    ]
}
