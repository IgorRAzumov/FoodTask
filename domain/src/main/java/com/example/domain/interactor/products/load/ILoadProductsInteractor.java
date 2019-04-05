package com.example.domain.interactor.products.load;

import com.example.domain.model.Product;

import java.util.List;

import io.reactivex.Flowable;

public interface ILoadProductsInteractor {
    Flowable<List<Product>> loadAllProducts();

    Flowable<List<Product>> loadProductsByCountry(long id);
}
