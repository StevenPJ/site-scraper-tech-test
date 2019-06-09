package co.uk.sainsburys.repository.creator;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.driven.data.ProductCreator;

import java.math.BigDecimal;

public class ScrapedProductCreator implements ProductCreator {
    @Override
    public Product create(String title, String description, String calories, String price) {
        return Product.builder()
            .title(title)
            .description(description)
            .calories(makeCalories(calories))
            .price(new Money(new BigDecimal(price)))
            .build();
    }

    private static Integer makeCalories(String value) {
        if (value == null) { return null; }
        return Integer.valueOf(value.replaceAll("\\D+",""));
    }
}
