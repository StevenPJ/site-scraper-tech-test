package co.uk.sainsburys;

import co.uk.sainsburys.driver.GetProducts;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

@AllArgsConstructor
public class ConsoleClient implements CommandLineRunner {

    private GetProducts getProducts;
    private String link;

    @Override
    public void run(String... args) {
        getProducts.fromPage(link);
    }
}
