package com.example.foodtask.di.provider;

import com.example.data.local.db.ProductsDatabase;
import com.example.data.local.db.dao.CountriesDao;

import javax.inject.Inject;
import javax.inject.Provider;

public class CountriesDaoProvider implements Provider<CountriesDao> {
    private final CountriesDao countriesDao;

    @Inject
    public CountriesDaoProvider(ProductsDatabase productsDatabase) {
        countriesDao = productsDatabase.countriesDao();
    }

    @Override
    public CountriesDao get() {
        return countriesDao;
    }
}
