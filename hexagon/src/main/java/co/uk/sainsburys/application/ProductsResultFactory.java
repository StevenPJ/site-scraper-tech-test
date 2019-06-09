package co.uk.sainsburys.application;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsResultFactory {

    private ProductsResultFactory() {}

    public static ProductsResult getResult(List<Product> products, Money gross, Money vat) {
        List<ProductsResult.ProductDto> productDtos = products
            .stream()
            .map(ProductsResultFactory::getProductDto)
            .collect(Collectors.toList());

        ProductsResult.ResultTotal total = getTotal(gross.getAmount(), vat.getAmount());
        return new ProductsResult(productDtos, total);
    }

    private static ProductsResult.ProductDto getProductDto(Product product) {
        Number price = product.getPrice() == null ? 0 : product.getPrice().getAmount();
        return new ProductsResult.ProductDto(
            product.getTitle(),
            product.getCalories(),
            price,
            product.getDescription()
        );
    }

    private static ProductsResult.ResultTotal getTotal(Number gross, Number vat) {
        return new ProductsResult.ResultTotal(gross, vat);
    }

}
