package co.uk.sainsburys.application;

import lombok.Value;

import java.util.List;

@Value
public class ProductsResult {
    private List<ProductDto> results;
    private ResultTotal total;

    @Value
    public static class ProductDto {
        private String title;
        private Integer kcal_per_100g;
    }

    @Value
    public static class ResultTotal {
        private Number gross;
        private Number vat;
    }
}
