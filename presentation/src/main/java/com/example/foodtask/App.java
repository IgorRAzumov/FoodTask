package com.example.foodtask;

import android.app.Application;

import com.example.foodtask.di.DI;
import com.example.foodtask.di.module.AppModule;

import timber.log.Timber;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initTimber();
        initToothpick();

    }

    private void initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment());
        } else {
            Toothpick.setConfiguration(Configuration.forProduction());
        }

        Scope appScope = Toothpick.openScope(DI.APP_SCOPE);
        appScope.installModules(new AppModule(this));
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
