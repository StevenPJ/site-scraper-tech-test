package co.uk.sainsburys.application;

import lombok.Value;

import java.util.List;

@Value
public class ProductsResult {
    List<ProductDto> results;

    @Value
    public static class ProductDto {
        private String title;
    }
}
