package com.example.domain.interactor.products.load;

import com.example.domain.model.Product;
import com.example.domain.repository.IProductsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class LoadProductsInteractor implements ILoadProductsInteractor {
    private final IProductsRepository repository;

    @Inject
    public LoadProductsInteractor(IProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<List<Product>> loadAllProducts() {
        return repository.loadProducts();
    }
}
