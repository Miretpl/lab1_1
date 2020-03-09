/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    private Product product;
    private int quantity;
    private String discountCause;

    private Money discount;
    private Money totalCost;

    public OfferItem(String productId, BigDecimal productPrice, String productPriceCurrency, String productName, Date productSnapshotDate,
            String productType, int quantity) {
        this(productId, productPrice, productPriceCurrency, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productPriceCurrency, String productName, Date productSnapshotDate,
            String productType, int quantity, BigDecimal discount, String discountCause) {

        this.product = new Product(productId, productPrice, productPriceCurrency, productName, productType, productSnapshotDate);
        this.quantity = quantity;
        this.discount = new Money(discount, productPriceCurrency);
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount);
        }

        this.totalCost = new Money(productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue), productPriceCurrency);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public Money getDiscount() {
        return discount;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OfferItem offerItem = (OfferItem) o;
        return quantity == offerItem.quantity && Objects.equals(product, offerItem.product) && Objects.equals(discountCause,
                offerItem.discountCause) && Objects.equals(discount, offerItem.discount) && Objects.equals(totalCost, offerItem.totalCost);
    }

    @Override public int hashCode() {
        return Objects.hash(product, quantity, discountCause, discount, totalCost);
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (other == null) {
            return false;
        }

        if (!product.equals(other.product)) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        if (!discount.equals(other.discount)) {
            return false;
        }

        if (!totalCost.equals(totalCost)) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost.getDenomination();
            min = other.totalCost.getDenomination();
        } else {
            max = other.totalCost.getDenomination();
            min = totalCost.getDenomination();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
