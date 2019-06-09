package co.uk.sainsburys.repository.creator;

import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.driven.data.ProductCreator;

public class ScrapedProductCreator implements ProductCreator {
    @Override
    public Product create(String title, String description, String calories, String price) {
        return Product.builder()
            .build();
    }
}
