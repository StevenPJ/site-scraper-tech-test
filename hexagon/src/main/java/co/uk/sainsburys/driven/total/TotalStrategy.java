package co.uk.sainsburys.driven.total;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;

import java.util.List;

public interface TotalStrategy {
    Money calculateTotalGross(List<Product> products);
    Money calculateTotalVat(List<Product> products);
}
