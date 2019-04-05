package com.example.foodtask.view.frgment.products.page;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.domain.model.Country;
import com.example.domain.model.Product;
import com.example.foodtask.R;
import com.example.foodtask.di.DI;
import com.example.foodtask.di.module.ProductModule;
import com.example.foodtask.view.adapter.products.IProductsPresenter;
import com.example.foodtask.view.adapter.products.ProductAdapter;
import com.example.foodtask.view.dialog.ISelectQuantityPresenter;
import com.example.foodtask.view.dialog.SelectQuantityDialog;
import com.example.foodtask.view.frgment.BaseFragment;

import butterknife.BindView;
import toothpick.Scope;
import toothpick.Toothpick;

import static com.example.foodtask.di.DI.PRODUCT_SCOPE;


public class CountryProductsFragment extends BaseFragment implements CountryProductsView {
    @BindView(R.id.srl_fr_country_products)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_fr_country_products)
    RecyclerView recyclerView;

    @InjectPresenter
    CountryProductsPresenter presenter;

    private Country country;
    private ProductAdapter productsAdapter;

    public static CountryProductsFragment newInstance() {
        return new CountryProductsFragment();
    }


    public static Fragment newInstance(Country country) {
        CountryProductsFragment fragment = new CountryProductsFragment();
        fragment.country = country;
        return fragment;
    }

    public CountryProductsFragment() {
    }

    @ProvidePresenter
    CountryProductsPresenter providePresenter() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, PRODUCT_SCOPE);
        scope.installModules(new ProductModule());
        presenter = scope.getInstance(CountryProductsPresenter.class);
        presenter.setCountry(country);
        return presenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_country_products;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSwipeRefreshLayout();
        initRecycler();
    }

    @Override
    public void setProductsListPresenter(IProductsPresenter productsPresenter) {
        productsAdapter = new ProductAdapter(productsPresenter);
        recyclerView.setAdapter(productsAdapter);
    }

    @Override
    public void showSelectQuantityDialog(ISelectQuantityPresenter presenter, Product product) {
        SelectQuantityDialog selectQuantityDialog = SelectQuantityDialog.newInstance(presenter);
        selectQuantityDialog.show(getChildFragmentManager(), SelectQuantityDialog.TAG);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void productsLoaded() {
        productsAdapter.notifyDataSetChanged();//TODO replace diffUtil
    }

    @Override
    public void showErrorLoadData() {
        Toast.makeText(getContext(), getString(R.string.error_data_load), Snackbar.LENGTH_LONG).show();
    }


    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setEnabled(false);
    }
}
