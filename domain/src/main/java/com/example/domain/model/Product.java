package com.example.domain.model;

import java.math.BigDecimal;

public final class Product {
    private final long id;
    private final int country;
    private final BigDecimal price;
    private final String image;
    private final String name;
    private final String currency;
    private final String weightUnit;
    private final int weightUnitQuantity;


    private Product(Builder builder) {
        this.id = builder.id;
        this.country = builder.country;
        this.price = builder.price;
        this.image = builder.image;
        this.name = builder.name;
        this.currency = builder.currency;
        this.weightUnit = builder.weightUnit;
        this.weightUnitQuantity = builder.weightUnitQuantity;
    }

    public long getId() {
        return id;
    }

    public int getCountry() {
        return country;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getWeightUnitQuantity() {
        return weightUnitQuantity;
    }

    public String getCurrency() {
        return currency;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public final static class Builder {
        private int weightUnitQuantity;
        private long id;
        private int country;
        private BigDecimal price;
        private String image;
        private String name;
        private String currency;
        private String weightUnit;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setCountry(int country) {
            this.country = country;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setWeightUnit(String weightUnit) {
            this.weightUnit = weightUnit;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setWeightUnitQuantity(int weightUnitQuantity) {
            this.weightUnitQuantity = weightUnitQuantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
