package com.example.foodtask.view.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public final class MainPresenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProductsScreen();
        getViewState().showOrderScreen();
    }

    void onExitClick() {
        getViewState().closeMenu();
    }
}
