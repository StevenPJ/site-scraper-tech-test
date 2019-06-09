package co.uk.sainsburys.driven.total;

import co.uk.sainsburys.domain.Money;
import co.uk.sainsburys.domain.Product;
import co.uk.sainsburys.domain.VatRate;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GrossTotalStrategy implements TotalStrategy {

  private Integer vatRate;

  @Override
  public Money calculateTotalGross(List<Product> products) {
    return products.stream()
        .map(Product::getPrice)
        .reduce(new Money(0), Money::add);
  }

  @Override
  public Money calculateTotalVat(List<Product> products) {
    Money grossOfVatable = products.stream()
        .filter(p -> VatRate.STANDARD_RATE.equals(p.getVatRate())) // only want vatable items
        .map(Product::getPrice)
        .reduce(new Money(0), Money::add);
    return calculateVatFromGross(grossOfVatable, this.vatRate);
  }

  private Money calculateVatFromGross(Money gross, Integer rate) {
    Number divisor = 1. + (rate / 100.);
    return gross.subtract(gross.divide(divisor));
  }


}
