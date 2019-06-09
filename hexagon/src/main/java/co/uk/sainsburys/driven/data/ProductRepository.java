package co.uk.sainsburys.driven.data;

import co.uk.sainsburys.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> search(String pageLink);
}
