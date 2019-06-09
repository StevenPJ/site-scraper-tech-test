package co.uk.sainsburys.driven.data;

import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.domain.exception.PageLoadException;

import java.util.List;

public interface ProductRepository {
    List<Product> search(String pageLink) throws PageLoadException;
}
