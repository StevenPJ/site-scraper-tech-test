package co.uk.sainsburys.application;

import co.uk.sainsburys.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsResultFactory {

    private ProductsResultFactory() {}

    public static ProductsResult getResult(List<Product> products) {
        List<ProductsResult.ProductDto> productDtos = products
            .stream()
            .map(ProductsResultFactory::getProductDto)
            .collect(Collectors.toList());
        return new ProductsResult(productDtos);
    }

    private static ProductsResult.ProductDto getProductDto(Product product) {
        return new ProductsResult.ProductDto(
            product.getTitle()
        );
    }
}
