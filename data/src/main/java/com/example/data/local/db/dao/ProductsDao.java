package com.example.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.data.local.db.entity.ProductEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ProductEntity... products);

    @Query("SELECT * from product")
    Flowable<List<ProductEntity>> getAllProducts();


    @Query("SELECT * from product where country ==:countryId")
    Flowable<List<ProductEntity>> loadProductById(long countryId);
}
