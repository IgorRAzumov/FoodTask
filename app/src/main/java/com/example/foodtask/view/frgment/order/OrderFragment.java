package com.example.foodtask.view.frgment.order;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.foodtask.R;
import com.example.foodtask.view.frgment.BaseFragment;


public class OrderFragment extends BaseFragment implements OrderView {

    @InjectPresenter
    OrderPresenter presenter;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public OrderFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
