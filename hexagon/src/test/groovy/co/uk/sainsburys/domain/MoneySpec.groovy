package co.uk.sainsburys.domain

import co.uk.sainsburys.domain.exception.InvalidMoneyOperationException
import spock.lang.Specification
import spock.lang.Unroll


class MoneySpec extends Specification {

    @Unroll
    def "#a plus #b should be #c"() {
        expect:
        a.add(b).getAmount() == c
        where:
        a                | b                 | c
        new Money(5)     | new Money(7)      | 12
        new Money(0)     | new Money(1)      | 1
        new Money(0.5)   | new Money(0.4)    | 0.9
        new Money(0.499) | new Money(0.001)  | 0.5
        new Money(0)     | new Money(0.005)  | 0.01
        new Money(0)     | new Money(0.0049) | 0
    }

    @Unroll
    def "#a subtract #b should be #c"() {
        expect:
        a.subtract(b).getAmount() == c
        where:
        a                | b               | c
        new Money(10)    | new Money(5)    | 5
        new Money(1.55)  | new Money(0.55) | 1
        new Money(0.499) | new Money(0.4)  | 0.1
        new Money(0.499) | new Money(0)    | 0.5
    }

    def "should throw InvalidMoneyOperation exception when negative money created"() {
        when:
        new Money(1).subtract(new Money(2))
        then:
        thrown InvalidMoneyOperationException
    }

    @Unroll
    def "#a divided by #b should be #c"() {
        expect:
        a.divide(b).getAmount() == c
        where:
        a            | b | c
        new Money(5) | 1 | 5
        new Money(0) | 1 | 0
        new Money(1) | 3 | 0.33
        new Money(2) | 3 | 0.67
    }

    def "divide by 0 should throw Arithmetic Exception"() {
        when:
        new Money(5).divide(0)
        then:
        thrown ArithmeticException
    }

}