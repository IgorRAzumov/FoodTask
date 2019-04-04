package com.example.foodtask.view.frgment.products;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.foodtask.R;
import com.example.foodtask.di.DI;
import com.example.foodtask.di.module.ProductModule;
import com.example.foodtask.view.frgment.BaseFragment;

import butterknife.BindView;
import toothpick.Scope;
import toothpick.Toothpick;

import static com.example.foodtask.di.DI.PRODUCT_SCOPE;


public class MainProductsFragment extends BaseFragment implements MainProductsView {
    @BindView(R.id.tb_fr_main_products)
    Toolbar toolbar;
    @BindView(R.id.tl_fr_main_products)
    TableLayout tableLayout;
    @BindView(R.id.vp_fr_main_products)
    ViewPager productsViewPager;

    @InjectPresenter
    MainProductsPresenter presenter;

    public static MainProductsFragment newInstance() {
        return new MainProductsFragment();
    }

    public MainProductsFragment() {

    }

    @ProvidePresenter
    MainProductsPresenter providePresenter() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, PRODUCT_SCOPE);
        scope.installModules(new ProductModule());
        presenter = scope.getInstance(MainProductsPresenter.class);
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_products;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorLoadData() {

    }

    @Override
    public void countriesDataLoaded() {

    }
}
