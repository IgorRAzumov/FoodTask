package com.example.domain.model;

import java.math.BigDecimal;

public final class OrderItem {
    private final float weight;
    private final BigDecimal sum;
    private final Product product;

    public OrderItem(float weight, BigDecimal sum, Product product) {
        this.weight = weight;
        this.sum = sum;
        this.product = product;
    }

    public float getWeight() {
        return weight;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public String getProductName() {
        return product.getName();
    }

    public String getProductWeightUnit() {
        return product.getWeightUnit();
    }

    public String getCurrency() {
        return product.getCurrency();
    }

    public int getWeightUnitQuantity() {
        return product.getWeightUnitQuantity();
    }

    public BigDecimal getPricePerUnit() {
        return product.getPrice();
    }
}
