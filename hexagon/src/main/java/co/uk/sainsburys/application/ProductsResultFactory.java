package co.uk.sainsburys.application;

import co.uk.sainsburys.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsResultFactory {

    private ProductsResultFactory() {}

    public static ProductsResult getResult(List<Product> products, Number gross, Number vat) {
        List<ProductsResult.ProductDto> productDtos = products
            .stream()
            .map(ProductsResultFactory::getProductDto)
            .collect(Collectors.toList());
        ProductsResult.ResultTotal total = getTotal(gross, vat);
        return new ProductsResult(productDtos, total);
    }

    private static ProductsResult.ProductDto getProductDto(Product product) {
        return new ProductsResult.ProductDto(
            product.getTitle()
        );
    }

    private static ProductsResult.ResultTotal getTotal(Number gross, Number vat) {
        return new ProductsResult.ResultTotal(gross, vat);
    }

}
