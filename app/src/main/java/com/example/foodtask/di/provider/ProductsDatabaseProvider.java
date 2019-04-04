package com.example.foodtask.di.provider;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.local.db.ProductsDatabase;
import com.example.data.local.db.entity.CountryEntity;
import com.example.data.local.db.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProductsDatabaseProvider implements Provider<ProductsDatabase> {
    private final ProductsDatabase database;

    @Inject
    public ProductsDatabaseProvider(Context context) {
        database = Room
                .databaseBuilder(context, ProductsDatabase.class, "demo-db")
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        new Runnable() {
                            @Override
                            public void run() {
                                database.countriesDao().insertAll(createDummyCountries());
                                database.productsDao().insertAll(createDummyData());
                            }
                        };
                    }
                })
                .build();
    }

    private CountryEntity[] createDummyCountries() {
        List<CountryEntity> countryEntities= new ArrayList<>();
        countryEntities.add(new CountryEntity("Россия"));
        countryEntities.add(new CountryEntity("Белоруссия"));
        return countryEntities.toArray(new CountryEntity[0]);
    }

    private ProductEntity[] createDummyData() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("свекла","sddssdf" ,42,1,1));
        productEntities.add(new ProductEntity("морковь","sddssdf" ,50,2,1));
        productEntities.add(new ProductEntity("лук","sddssdf" ,77,1,1));
        productEntities.add(new ProductEntity("петрушка","sddssdf" ,23,2,1));
        productEntities.add(new ProductEntity("укроп","sddssdf" ,43,1,1));
        productEntities.add(new ProductEntity("помидоры","sddssdf" ,62,2,1));
        productEntities.add(new ProductEntity("огурцы","sddssdf" ,32,1,1));
        productEntities.add(new ProductEntity("sdsfs","sddssdf" ,37,2,1));
        return productEntities.toArray(new ProductEntity[0]);
    }

    @Override
    public ProductsDatabase get() {
        return database;
    }
}
