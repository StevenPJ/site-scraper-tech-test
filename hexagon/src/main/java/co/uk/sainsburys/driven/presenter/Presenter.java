package co.uk.sainsburys.driven.presenter;

import co.uk.sainsburys.application.ProductsResult;

public interface Presenter {
    void show(ProductsResult result);
    void showErrorMessage(String message);
}
