package com.example.data.repository.map;

import com.example.data.local.db.entity.CountryEntity;
import com.example.data.local.db.entity.ProductEntity;
import com.example.domain.model.Country;
import com.example.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

public final class DataMap implements IDataMap {
    @Override
    public List<Product> mapToProducts(List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        for (ProductEntity entity : productEntities) {
            products.add(new Product.Builder()
                    .setId(entity.getId())
                    .setCountry(entity.getCountry())
                    .setPrice(entity.getPrice())
                    .setCurrency(entity.getCurrency())
                    .setWeightUnit(entity.getWeightUnit())
                    .setWeightUnitQuantity(entity.getWeightUnitQuantity())
                    .setImage(entity.getImage())
                    .setName(entity.getName())
                    .build());
        }
        return products;
    }

    @Override
    public List<Country> mapToCountries(List<CountryEntity> countryEntities) {
        List<Country> countries = new ArrayList<>();
        for (CountryEntity entity : countryEntities) {
            countries.add(new Country(entity.getId(), entity.getName()));
        }
        return countries;
    }
}
