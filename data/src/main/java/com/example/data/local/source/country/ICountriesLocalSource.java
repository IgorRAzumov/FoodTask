package com.example.data.local.source.country;

import com.example.data.local.db.entity.CountryEntity;

import java.util.List;

import io.reactivex.Single;

public interface ICountriesLocalSource {
    Single<List<CountryEntity>> loadCountries();
}
