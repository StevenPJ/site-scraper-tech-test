package co.uk.sainsburys;

import co.uk.sainsburys.application.ProductsResult;
import co.uk.sainsburys.driven.presenter.Presenter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Value;

public class ConsoleWriter implements Presenter {

    private static final Gson jsonBuilder = new GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create();

    @Override
    public void show(ProductsResult result) {
        System.out.println(jsonBuilder.toJson(result));
    }

    @Override
    public void showErrorMessage(String message) {
        JsonError error = new JsonError(message);
        System.out.println(jsonBuilder.toJson(error));
    }

    @Value
    class JsonError {
        private String error;
    }
}
