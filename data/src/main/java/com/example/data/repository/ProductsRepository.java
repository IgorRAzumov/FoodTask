package com.example.data.repository;

import android.annotation.SuppressLint;

import com.example.data.local.source.country.ICountriesLocalSource;
import com.example.data.local.source.product.IProductsLocalSource;
import com.example.data.repository.map.IDataMap;
import com.example.domain.model.Country;
import com.example.domain.model.Product;
import com.example.domain.repository.IProductsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.ReplaySubject;
import timber.log.Timber;

@SuppressLint("UseSparseArrays")
public final class ProductsRepository implements IProductsRepository {
    private final IProductsLocalSource localDataSource;
    private final ICountriesLocalSource countriesDataSource;
    private final IDataMap dataMap;
    private final List<Product> productsCache;
    private final Map<Long, ReplaySubject<List<Product>>> productsSubjectsMap;


    @Inject
    public ProductsRepository(IProductsLocalSource localDataSource,
                              ICountriesLocalSource countriesDataSource, IDataMap dataMap) {
        this.localDataSource = localDataSource;
        this.countriesDataSource = countriesDataSource;
        this.dataMap = dataMap;
        productsCache = new ArrayList<>();
        productsSubjectsMap = new HashMap<>();
    }

    @Override
    public Flowable<List<Product>> loadProducts() {
        return localDataSource
                .loadProducts()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToProducts)
                .doOnNext(productsCache::addAll)
                .doOnNext(this::updatesCountriesSubject);

    }

    //не стал делать отдельные запросы в базу для вкладок табов, по той причине, что товара скорее всего
    //буде крайне много, значит встанет вопрос о пагинации, но помимо пагинации, надо как-то
    // синхронизировать контент на вкладках(хотя, может оверинжиниринг пошел) Если подход не очень - сообщите
    @Override
    public Flowable<List<Product>> loadProductsByCountry(long countryId) {
        ReplaySubject<List<Product>> subject = ReplaySubject.create();
        if (!productsCache.isEmpty()) {
            subject.onNext(filterProductsByCountry(countryId));
        }
        productsSubjectsMap.put(countryId, subject);
        return subject.toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<List<Country>> loadCountries() {
        return countriesDataSource
                .loadCountries()
                .onErrorReturn(throwable -> {
                    Timber.e(throwable);
                    return Collections.emptyList();
                })
                .map(dataMap::mapToCountries);
    }

    private List<Product> filterProductsByCountry(long countryId) {
        List<Product> result = new ArrayList<>();
        for (Product product : productsCache) {
            if (product.getCategory() == countryId) {
                result.add(product);
            }
        }
        return result;
    }

    private void updatesCountriesSubject(List<Product> products) {
        for (ReplaySubject<List<Product>> subject : productsSubjectsMap.values()) {
            subject.onNext(products);
        }
    }

    private void clear(){

    }
}
