package com.example.foodtask.di.module;

import android.content.Context;

import com.example.data.local.db.ProductsDatabase;
import com.example.data.local.db.dao.CountriesDao;
import com.example.data.local.db.dao.ProductsDao;
import com.example.data.local.source.country.CountriesLocalSource;
import com.example.data.local.source.country.ICountriesLocalSource;
import com.example.data.local.source.product.IProductsLocalSource;
import com.example.data.local.source.product.ProductsLocalSource;
import com.example.data.repository.ProductsRepository;
import com.example.data.repository.map.DataMap;
import com.example.data.repository.map.IDataMap;
import com.example.domain.repository.IProductsRepository;
import com.example.foodtask.di.provider.CountriesDaoProvider;
import com.example.foodtask.di.provider.ProductsDaoProvider;
import com.example.foodtask.di.provider.ProductsDatabaseProvider;
import com.example.foodtask.utils.image.loader.IImageLoader;
import com.example.foodtask.utils.image.loader.glide.GlideImageLoader;
import com.example.foodtask.utils.sheduler.ISchedulersProvider;
import com.example.foodtask.utils.sheduler.SchedulersProvider;

import toothpick.config.Module;

public class AppModule extends Module {

    public AppModule(Context context) {
        bind(Context.class).toInstance(context);

        bind(ISchedulersProvider.class).toInstance(new SchedulersProvider());
        bind(IDataMap.class).toInstance(new DataMap());
        bind(IImageLoader.class).toInstance(new GlideImageLoader());

        bind(ProductsDatabase.class).toProvider(ProductsDatabaseProvider.class);
        bind(ProductsDao.class).toProvider(ProductsDaoProvider.class);
        bind(CountriesDao.class).toProvider(CountriesDaoProvider.class);

        bind(IProductsLocalSource.class).to(ProductsLocalSource.class).singletonInScope();
        bind(ICountriesLocalSource.class).to(CountriesLocalSource.class).singletonInScope();
        bind(IProductsRepository.class).to(ProductsRepository.class).singletonInScope();
    }
}
