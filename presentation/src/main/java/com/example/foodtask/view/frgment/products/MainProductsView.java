package com.example.foodtask.view.frgment.products;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.domain.model.Country;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
interface MainProductsView extends MvpView {
    void showProgress();

    void hideProgress();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorLoadData();

    void countriesDataLoaded(List<Country> countries);

    void showMenu();
}
