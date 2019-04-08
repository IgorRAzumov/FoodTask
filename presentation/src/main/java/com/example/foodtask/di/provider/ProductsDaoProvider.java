package com.example.foodtask.di.provider;

import com.example.data.local.db.ProductsDatabase;
import com.example.data.local.db.dao.ProductsDao;

import javax.inject.Inject;
import javax.inject.Provider;

public final class ProductsDaoProvider implements Provider<ProductsDao> {
    private final ProductsDao productsDao;

    @Inject
    public ProductsDaoProvider(ProductsDatabase productsDatabase) {
        productsDao = productsDatabase.productsDao();
    }

    @Override
    public ProductsDao get() {
        return productsDao;
    }
}
