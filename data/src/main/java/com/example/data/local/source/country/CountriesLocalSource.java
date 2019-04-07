package com.example.data.local.source.country;

import com.example.data.local.db.dao.CountriesDao;
import com.example.data.local.db.entity.CountryEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public final class CountriesLocalSource implements ICountriesLocalSource {
    private final CountriesDao countriesDao;

    @Inject
    public CountriesLocalSource(CountriesDao countriesDao) {
    this.countriesDao=countriesDao;
    }

    @Override
    public Flowable<List<CountryEntity>> loadCountries() {
        return countriesDao.getAllCountries();
    }
}
