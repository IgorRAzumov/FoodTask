package com.example.foodtask.di.module;

import com.example.domain.interactor.counties.ILoadCountriesInteractor;
import com.example.domain.interactor.counties.LoadCountriesInteractor;
import com.example.domain.interactor.products.load.ILoadProductsInteractor;
import com.example.domain.interactor.products.load.LoadProductsInteractor;

import toothpick.config.Module;

public class ProductModule extends Module {

    public ProductModule() {
        bind(ILoadProductsInteractor.class).to(LoadProductsInteractor.class);
        bind(ILoadCountriesInteractor.class).to(LoadCountriesInteractor.class);
    }
}
