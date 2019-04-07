package com.example.foodtask.core;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class BasePresenter<T extends MvpView> extends MvpPresenter<T> {
    private final CompositeDisposable compositeDisposable;

    public BasePresenter() {
        this.compositeDisposable = new CompositeDisposable();
    }

    protected void addToDisposables(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
