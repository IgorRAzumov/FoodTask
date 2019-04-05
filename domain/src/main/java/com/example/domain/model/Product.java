package com.example.domain.model;

public class Product {
    private final long id;
    private final int country;
    private final int category;
    private final double price;
    private final String image;
    private final String name;


    private Product(Builder builder) {
        this.id = builder.id;
        this.country = builder.country;
        this.price = builder.price;
        this.image = builder.image;
        this.name = builder.name;
        this.category = builder.category;
    }

    public long getId() {
        return id;
    }

    public int getCountry() {
        return country;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public final static class Builder {
        private int category;
        private long id;
        private int country;
        private double price;
        private String image;
        private String name;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setCountry(int country) {
            this.country = country;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCategory(int category) {
            this.category = category;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
