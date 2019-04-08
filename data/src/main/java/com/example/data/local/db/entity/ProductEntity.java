package com.example.data.local.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.example.data.local.db.converter.BigDecimalConverter;

import java.math.BigDecimal;

@Entity(tableName = "product")
public final class ProductEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "price")
    @TypeConverters(BigDecimalConverter.class)
    private BigDecimal price;

    @ColumnInfo(name = "country")
    private int country;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "currency")
    private String currency;

    @ColumnInfo(name = "weight_unit")
    private String weightUnit;

    @ColumnInfo(name = "weight_unit_quantity")
    private int weightUnitQuantity;

    public ProductEntity(int country, int weightUnitQuantity,
                          BigDecimal price,String name, String image, String currency, String weightUnit) {
        this.price = price;
        this.country = country;
        this.name = name;
        this.image = image;
        this.currency = currency;
        this.weightUnit = weightUnit;
        this.weightUnitQuantity = weightUnitQuantity;
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

    public BigDecimal getPrice() {
        return price;
    }

    public int getCountry() {
        return country;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public int getWeightUnitQuantity() {
        return weightUnitQuantity;
    }
}
