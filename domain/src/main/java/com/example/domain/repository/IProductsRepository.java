package com.example.domain.repository;

import com.example.domain.model.Country;
import com.example.domain.model.Product;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IProductsRepository {
    Flowable<List<Product>> loadProducts();

    Flowable<List<Product>> loadProductsByCountry(int countryId);

    Single<List<Country>> loadCountries();
}
