package com.example.foodtask.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.foodtask.R;
import com.example.foodtask.core.IFragmentContainer;
import com.example.foodtask.di.DI;
import com.example.foodtask.di.module.ProductModule;
import com.example.foodtask.view.frgment.order.OrderFragment;
import com.example.foodtask.view.frgment.products.MainProductsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import toothpick.Toothpick;

import static com.example.foodtask.di.DI.PRODUCT_SCOPE;

public class MainActivity extends MvpAppCompatActivity implements MainView, IFragmentContainer {
    @BindView(R.id.dl_root_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nv_act_main_nav_view)
    NavigationView navigationView;

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toothpick.openScopes(DI.APP_SCOPE, PRODUCT_SCOPE).installModules(new ProductModule());

        initNavigationView();
    }

    @Override
    public void closeMenu() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void openMenu() {
        drawerLayout.openDrawer(GravityCompat.START);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toothpick.closeScope(DI.PRODUCT_SCOPE);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void initNavigationView() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            presenter.onExitClick();
            return true;
        });
    }
}
