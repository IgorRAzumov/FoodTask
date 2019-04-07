package com.example.foodtask.di.module;

import com.example.domain.interactor.calculator.CalculateInteractor;
import com.example.domain.interactor.calculator.ICalculateInteractor;
import com.example.domain.interactor.counties.ILoadCountriesInteractor;
import com.example.domain.interactor.counties.LoadCountriesInteractor;
import com.example.domain.interactor.order.CreateOrderInteractor;
import com.example.domain.interactor.order.ICreateOrderInteractor;
import com.example.domain.interactor.products.load.ILoadProductsInteractor;
import com.example.domain.interactor.products.load.LoadProductsInteractor;

import toothpick.config.Module;

public class ProductModule extends Module {

    public ProductModule() {
        bind(ILoadProductsInteractor.class).to(LoadProductsInteractor.class);
        bind(ILoadCountriesInteractor.class).to(LoadCountriesInteractor.class);
        bind(ICreateOrderInteractor.class).toInstance(new CreateOrderInteractor());
        bind(ICalculateInteractor.class).toInstance(new CalculateInteractor());
    }
}
