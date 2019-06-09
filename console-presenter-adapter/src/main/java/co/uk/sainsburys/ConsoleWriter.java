package co.uk.sainsburys;

import co.uk.sainsburys.application.ProductsResult;
import co.uk.sainsburys.driven.presenter.Presenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConsoleWriter implements Presenter {

    private static final Gson jsonBuilder = new GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create();

    @Override
    public void show(ProductsResult result) {
        System.out.println(jsonBuilder.toJson(result));
    }
}
