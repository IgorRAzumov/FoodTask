package com.example.foodtask.view.frgment.products.country;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.foodtask.R;
import com.example.foodtask.view.frgment.BaseFragment;


public class CountryProductsFragment extends BaseFragment implements CountryProductsView {

    @InjectPresenter
    CountryProductsPresenter presenter;

    public static CountryProductsFragment newInstance() {
        return new CountryProductsFragment();
    }

    public CountryProductsFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_country_products;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
