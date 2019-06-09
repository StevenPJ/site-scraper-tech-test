package co.uk.sainsburys.driven.total;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;

import java.util.List;

public class GrossTotalStrategy implements TotalStrategy {

  @Override
  public Money calculateTotalGross(List<Product> products) {
    return products.stream()
        .map(Product::getPrice)
        .reduce(new Money(0), Money::add);
  }

  @Override
  public Money calculateTotalVat(List<Product> products) {
    return calculateVatFromGross(calculateTotalGross(products), 20);
  }

  private Money calculateVatFromGross(Money gross, Integer rate) {
    Number divisor = 1. + (rate / 100.);
    return gross.subtract(gross.divide(divisor));
  }


}
