package co.uk.sainsburys.repository;

import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.driven.data.Dao;
import co.uk.sainsburys.driven.data.ProductCreator;
import co.uk.sainsburys.driven.data.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ScrapedProductRepository implements ProductRepository {

    Dao<List<ProductDetails>> dao;
    ProductCreator creator;

    @Override
    public List<Product> search(String pageLink) {
        return dao.extractFrom(pageLink).stream()
            .map(p -> creator.create(
                p.getTitle(),
                p.getDescription(),
                p.getCalories(),
                p.getPrice()
            ))
            .collect(Collectors.toList());
    }
}
