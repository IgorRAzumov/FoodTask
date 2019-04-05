package com.example.foodtask.view.activity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface MainView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showProductsScreen();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showOrderScreen();

    void closeMenu();
}
