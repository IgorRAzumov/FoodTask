package com.example.foodtask.view.frgment.products.page;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.foodtask.view.adapter.products.IProductsPresenter;
import com.example.foodtask.view.dialog.ISelectQuantityPresenter;

@StateStrategyType(AddToEndSingleStrategy.class)
interface CountryProductsView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSelectQuantityDialog(ISelectQuantityPresenter selectQuantityPresenter);

    void showProgress();

    void hideProgress();

    void productsLoaded();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorLoadData();

    void setProductsListPresenter(IProductsPresenter productsPresenter);
}
