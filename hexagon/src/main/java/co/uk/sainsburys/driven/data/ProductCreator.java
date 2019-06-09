package co.uk.sainsburys.driven.data;

import co.uk.sainsburys.domain.Product;

public interface ProductCreator {
    Product create(String title, String description, String calories, String price);
}
