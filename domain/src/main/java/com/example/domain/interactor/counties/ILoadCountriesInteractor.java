package com.example.domain.interactor.counties;

import com.example.domain.model.Country;

import java.util.List;

import io.reactivex.Single;

public interface ILoadCountriesInteractor {
    Single<List<Country>> loadAllCountries();
}
