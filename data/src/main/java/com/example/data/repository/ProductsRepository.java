package com.example.data.repository;

import android.annotation.SuppressLint;

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

@SuppressLint("UseSparseArrays")
public final class ProductsRepository implements IProductsRepository {
    private final IProductsLocalSource productsLocalDataSource;
    private final ICountriesLocalSource countriesLocalDataSource;
    private final IDataMap dataMap;


    @Inject
    public ProductsRepository(IProductsLocalSource localDataSource,
                              ICountriesLocalSource countriesDataSource, IDataMap dataMap) {
        this.productsLocalDataSource = localDataSource;
        this.countriesLocalDataSource = countriesDataSource;
        this.dataMap = dataMap;
    }

    @Override
    public Flowable<List<Product>> loadProducts() {
        return productsLocalDataSource
                .loadProducts()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToProducts);
    }

    @Override
    public Flowable<List<Product>> loadProductsByCountry(long countryId) {
        return productsLocalDataSource
                .loadProductsByCountry(countryId)
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToProducts);
    }

    @Override
    public Flowable<List<Country>> loadCountries() {
        return countriesLocalDataSource
                .loadCountries()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToCountries);
    }

}
