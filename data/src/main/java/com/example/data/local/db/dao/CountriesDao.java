package com.example.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.data.local.db.entity.CountryEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface CountriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(CountryEntity...countryEntities);

    @Query("SELECT * from country")
    Single<List<CountryEntity>> getAllCountries();
}
