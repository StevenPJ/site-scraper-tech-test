package co.uk.sainsburys;

import co.uk.sainsburys.application.ProductsResult;
import co.uk.sainsburys.driven.presenter.Presenter;

public class ConsoleWriter implements Presenter {

    @Override
    public void show(ProductsResult result) {
        System.out.println(result);
    }
}
