package com.example.data.local.source.product;

import com.example.data.local.db.dao.ProductsDao;
import com.example.data.local.db.entity.ProductEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;


public final class ProductsLocalSource implements IProductsLocalSource {
    private final ProductsDao productsDao;

    @Inject
    public ProductsLocalSource(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    @Override
    public Flowable<List<ProductEntity>> loadProducts() {
        return productsDao.getAllProducts();
    }
}
