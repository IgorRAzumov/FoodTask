package com.example.data.repository.map;

import com.example.data.local.db.entity.CountryEntity;
import com.example.data.local.db.entity.ProductEntity;
import com.example.domain.model.Country;
import com.example.domain.model.Product;

import java.util.List;

public interface IDataMap {
    List<Product> mapToProducts(List<ProductEntity> productEntities);

    List<Country> mapToCountries(List<CountryEntity> countryEntities);
}
