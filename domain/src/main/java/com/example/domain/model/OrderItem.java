package com.example.domain.model;

public final class OrderItem {
    private final float weight;
    private final double sum;
    private final Product product;

    public OrderItem(float weight, double sum, Product product) {
        this.weight = weight;
        this.sum = sum;
        this.product = product;
    }

    public float getWeight() {
        return weight;
    }

    public double getSum() {
        return sum;
    }

    public double getUnit() {
        return product.getUnit();
    }

    public double getPrice() {
        return product.getPrice();
    }

   public String getName() {
        return product.getName();
    }
}
