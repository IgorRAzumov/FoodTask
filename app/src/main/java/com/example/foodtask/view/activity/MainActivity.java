package com.example.foodtask.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.foodtask.R;
import com.example.foodtask.view.frgment.order.OrderFragment;
import com.example.foodtask.view.frgment.products.MainProductsFragment;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showProductsScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_act_main_frame, MainProductsFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void showOrderScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_act_secondary_frame, OrderFragment.newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
