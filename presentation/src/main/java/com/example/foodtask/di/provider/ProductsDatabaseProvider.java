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
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProductsDatabaseProvider implements Provider<ProductsDatabase> {
    private final ProductsDatabase database;

    @Inject
    public ProductsDatabaseProvider(Context context) {
        database = Room
                .databaseBuilder(context, ProductsDatabase.class, ProductsDatabase.NAME)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            database.countriesDao().insertAll(createDummyCountries());
                            database.productsDao().insertAll(createDummyData());

                        });
                    }
                })
                .build();
    }

    private CountryEntity[] createDummyCountries() {
        List<CountryEntity> countryEntities = new ArrayList<>();
        countryEntities.add(new CountryEntity("Россия"));
        countryEntities.add(new CountryEntity("Беларусь"));
        return countryEntities.toArray(new CountryEntity[0]);
    }

    private ProductEntity[] createDummyData() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("Свекла", "file:///android_asset/beet.jpg", 42, 1, 1));
        productEntities.add(new ProductEntity("Мморковь", "file:///android_asset/carrot.jpg", 50, 2, 1));
        productEntities.add(new ProductEntity("Лук", "file:///android_asset/onion.jpg", 77, 1, 2));
        productEntities.add(new ProductEntity("Петрушка", "file:///android_asset/parsley.jpg", 23, 2, 2));
        productEntities.add(new ProductEntity("Укроп", "file:///android_asset/dill.jpg", 43, 1, 1));
        productEntities.add(new ProductEntity("Помидоры", "file:///android_asset/tomato.jpg", 62, 2, 2));
        productEntities.add(new ProductEntity("Огурцы", "file:///android_asset/cucumber.jpg", 32, 1, 1));
        return productEntities.toArray(new ProductEntity[0]);
    }

    @Override
    public ProductsDatabase get() {
        return database;
    }
}
