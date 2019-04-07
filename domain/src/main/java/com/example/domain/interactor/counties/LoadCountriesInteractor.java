package com.example.domain.interactor.counties;

import com.example.domain.model.Country;
import com.example.domain.repository.IProductsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public final class LoadCountriesInteractor implements ILoadCountriesInteractor {
    private final IProductsRepository repository;

    @Inject
    public LoadCountriesInteractor(IProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<List<Country>> loadAllCountries() {
        return repository.loadCountries();
    }
}
