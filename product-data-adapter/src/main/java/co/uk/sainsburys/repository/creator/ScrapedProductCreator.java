package co.uk.sainsburys.repository.creator;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.domain.VatRate;
import co.uk.sainsburys.driven.data.ProductCreator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScrapedProductCreator implements ProductCreator {

    private VatRate defaultRate;

    @Override
    public Product create(String title, String description, String calories, String price) {
        return Product.builder()
            .title(title)
            .description(description)
            .calories(makeCalories(calories))
            .price(makePrice(price))
            .vatRate(defaultRate)
            .build();
    }

    private static Integer makeCalories(String value) {
        if (value == null) { return null; }
        return Integer.valueOf(value.replaceAll("\\D+",""));
    }

    private static Money makePrice(String price) {
        if (price == null) { return new Money(0); }
        return new Money(Double.valueOf(price.replaceAll("[^0-9?!\\.]","")));
    }
}
