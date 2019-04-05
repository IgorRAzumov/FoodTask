package com.example.domain.interactor.counties;

import com.example.domain.model.Country;

import java.util.List;

import io.reactivex.Flowable;

public interface ILoadCountriesInteractor {
   Flowable<List<Country>> loadAllCountries();
}
