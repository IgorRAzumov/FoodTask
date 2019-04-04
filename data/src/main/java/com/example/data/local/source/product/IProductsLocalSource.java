package com.example.data.local.source.product;

import com.example.data.local.db.entity.ProductEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface IProductsLocalSource {
    Flowable<List<ProductEntity>> loadProducts();
}
