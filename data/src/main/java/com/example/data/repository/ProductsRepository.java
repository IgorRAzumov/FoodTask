package com.example.data.repository;

import com.example.data.local.source.country.ICountriesLocalSource;
import com.example.data.local.source.product.IProductsLocalSource;
import com.example.data.repository.map.IDataMap;
import com.example.domain.model.Country;
import com.example.domain.model.Product;
import com.example.domain.repository.IProductsRepository;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import timber.log.Timber;

public class ProductsRepository implements IProductsRepository {
    private final IProductsLocalSource localDataSource;
    private final ICountriesLocalSource countriesDataSource;
    private final IDataMap dataMap;

    @Inject
    public ProductsRepository(IProductsLocalSource localDataSource,
                              ICountriesLocalSource countriesDataSource, IDataMap dataMap) {
        this.localDataSource = localDataSource;
        this.countriesDataSource = countriesDataSource;
        this.dataMap = dataMap;
    }

    @Override
    public Flowable<List<Product>> loadProducts() {
        return localDataSource
                .loadProducts()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToProducts);
    }

    @Override
    public Flowable<List<Product>> loadProductsByCountry(long countryId) {
       return localDataSource
                .loadProducts()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToProducts);
    }

    @Override
    public Flowable<List<Country>> loadCountries() {
        return countriesDataSource
                .loadCountries()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                }).map(dataMap::mapToCountries);
    }
}
