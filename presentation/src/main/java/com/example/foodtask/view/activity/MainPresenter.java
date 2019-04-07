package com.example.foodtask.view.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProductsScreen();
        getViewState().showOrderScreen();
    }

    void menuItemClick() {
        getViewState().closeMenu();
    }
}