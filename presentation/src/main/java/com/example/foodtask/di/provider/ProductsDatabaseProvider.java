package com.example.foodtask.di.provider;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.data.local.db.ProductsDatabase;
import com.example.data.local.db.entity.CountryEntity;
import com.example.data.local.db.entity.ProductEntity;

import java.math.BigDecimal;
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
        productEntities.add(new ProductEntity(1, 1, 1, new BigDecimal(52.5), "Свекла", "file:///android_asset/beet.jpg", "P", "Кг"));
        productEntities.add(new ProductEntity(1, 2, 1, new BigDecimal(65), "Мморковь", "file:///android_asset/carrot.jpg", "P", "Кг"));
        productEntities.add(new ProductEntity(1, 2, 1, new BigDecimal(55.3), "Лук", "file:///android_asset/onion.jpg", "P", "Кг"));
        productEntities.add(new ProductEntity(2, 2, 1,new BigDecimal(500), "Петрушка", "file:///android_asset/parsley.jpg", "P", "Кг"));
        productEntities.add(new ProductEntity(2, 1, 1,new BigDecimal(435), "Укроп", "file:///android_asset/dill.jpg", "P", "Кг"));
        productEntities.add(new ProductEntity(1, 1, 1,new BigDecimal(256), "Помидоры", "file:///android_asset/tomato.jpg", "P", "Кг"));
        productEntities.add(new ProductEntity(1, 1, 1,new BigDecimal(500), "Огурцы", "file:///android_asset/cucumber.jpg", "P", "Кг"));
        return productEntities.toArray(new ProductEntity[0]);
    }

    @Override
    public ProductsDatabase get() {
        return database;
    }
}
