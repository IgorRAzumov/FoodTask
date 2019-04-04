package com.example.foodtask.view.frgment.products;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainProductsView extends MvpView {
    void showProgress();

    void hideProgress();

    void showErrorLoadData();

    void countriesDataLoaded();
}
