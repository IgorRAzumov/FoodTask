package com.example.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.data.local.db.dao.CountriesDao;
import com.example.data.local.db.dao.ProductsDao;
import com.example.data.local.db.entity.CountryEntity;
import com.example.data.local.db.entity.ProductEntity;


@Database(entities = {CountryEntity.class, ProductEntity.class}, version = 1, exportSchema = false)
public abstract class ProductsDatabase extends RoomDatabase {
    public abstract ProductsDao productsDao();
    public abstract CountriesDao countriesDao();
}
