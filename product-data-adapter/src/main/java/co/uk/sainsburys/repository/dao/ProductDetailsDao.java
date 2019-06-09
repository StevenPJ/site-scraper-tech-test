package co.uk.sainsburys.repository.dao;

import co.uk.sainsburys.driven.data.Dao;
import co.uk.sainsburys.repository.ProductDetails;

import java.util.List;

public class ProductDetailsDao implements Dao<List<ProductDetails>> {

    @Override
    public List<ProductDetails> extractFrom(String page) {
        throw new UnsupportedOperationException();
    }
}
