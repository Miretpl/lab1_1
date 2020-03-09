package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private BigDecimal denomination;
    private String currency;

    public Money(BigDecimal denomination, String currency) {
        this.denomination = denomination;
        this.currency = currency;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public String getCurrency() {
        return currency;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(denomination, money.denomination) && Objects.equals(currency, money.currency);
    }

    @Override public int hashCode() {
        return Objects.hash(denomination, currency);
    }

    public int compareTo(Money totalCost) {
        return denomination.compareTo(totalCost.getDenomination());
    }
}
