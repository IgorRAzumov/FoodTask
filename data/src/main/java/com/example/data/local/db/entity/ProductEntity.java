package com.example.data.local.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "product")
public class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "category")
    private int category;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "country")
    private int country;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image")
    private String image;

    public ProductEntity(@NonNull String name, String image, double price, int country, int category) {
        this.name = name;
        this.price = price;
        this.country = country;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public int getCountry() {
        return country;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }
}
