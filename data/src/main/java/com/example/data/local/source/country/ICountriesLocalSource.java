package com.example.data.local.source.country;

import com.example.data.local.db.entity.CountryEntity;

import java.util.List;

import io.reactivex.Flowable;

public interface ICountriesLocalSource {
   Flowable<List<CountryEntity>> loadCountries();
}
