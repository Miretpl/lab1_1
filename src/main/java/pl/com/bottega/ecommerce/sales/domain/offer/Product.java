package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Product {

    private String id;
    private Money price;
    private String name;
    private String type;
    private Date snapshotDate;

    public Product(String id, BigDecimal price, String currency, String name, String type, Date snapshotDate) {
        this.id = id;
        this.price = new Money(price, currency);
        this.name = name;
        this.type = type;
        this.snapshotDate = snapshotDate;
    }

    public String getId() {
        return id;
    }

    public Money getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id)
               && Objects.equals(price, product.price)
               && Objects.equals(name, product.name)
               && Objects.equals(type, product.type)
               && Objects.equals(snapshotDate, product.snapshotDate);
    }

    @Override public int hashCode() {
        return Objects.hash(id, price, name, type, snapshotDate);
    }
}
