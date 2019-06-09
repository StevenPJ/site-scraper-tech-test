package co.uk.sainsburys.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class Money {
    private static final Integer ROUNDING_SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private BigDecimal amount;

    public Money(Number value) {
        BigDecimal strictValue = BigDecimal.valueOf(value.doubleValue());
        this.amount = strictValue.setScale(ROUNDING_SCALE, ROUNDING_MODE);
    }

    public Money add(Money other) {
        return new Money(other.amount.add(this.amount));
    }

    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }

    public Money divide(Number divisor) {
        Money strictDivisor = new Money(divisor);
        return new Money(this.amount.divide(strictDivisor.amount, ROUNDING_SCALE, ROUNDING_MODE));
    }
}
