package com.example.foodtask.view.frgment.products;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.domain.model.Country;
import com.example.foodtask.R;
import com.example.foodtask.core.BaseFragment;
import com.example.foodtask.di.DI;
import com.example.foodtask.di.module.ProductModule;
import com.example.foodtask.view.adapter.products_page.ProductsPageAdapter;
import com.example.foodtask.view.frgment.products.page.CountryProductsFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import toothpick.Scope;
import toothpick.Toothpick;

import static com.example.foodtask.di.DI.PRODUCT_SCOPE;


public class MainProductsFragment extends BaseFragment implements MainProductsView {
    @BindView(R.id.swl_fr_main_products)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tb_fr_main_products)
    Toolbar toolbar;
    @BindView(R.id.tl_fr_main_products)
    TabLayout tabLayout;
    @BindView(R.id.vp_fr_main_products)
    ViewPager productsViewPager;

    @InjectPresenter
    MainProductsPresenter presenter;

    private ProductsPageAdapter productsPageAdapter;

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
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSwipeRefreshLayout();
        initToolbar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_product_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
    public void showErrorLoadData() {
        Toast.makeText(getContext(), getString(R.string.error_data_load), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void countriesDataLoaded(List<Country> countries) {
        productsPageAdapter = new ProductsPageAdapter(getChildFragmentManager(),
                createFragments(countries), createFragmentsTitles(countries));
        productsViewPager.setAdapter(productsPageAdapter);
        tabLayout.setupWithViewPager(productsViewPager);
    }

    @Override
    public void showMenu() {
        getFragmentContainer().openMenu();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setEnabled(false);
    }

    private List<Fragment> createFragments(List<Country> countries) {
        List<Fragment> fragments = new ArrayList<>();
        Fragment currentPage = CountryProductsFragment.newInstance();
        fragments.add(currentPage);

        for (Country country : countries) {
            fragments.add(CountryProductsFragment.newInstance(country));
        }
        return fragments;
    }

    private List<String> createFragmentsTitles(List<Country> countries) {
        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.common_all));

        for (Country country : countries) {
            titles.add(country.getName());
        }
        return titles;
    }


    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity == null) {
            return;
        }
        toolbar.setTitleTextColor(ContextCompat.getColor(activity, R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(v -> presenter.onMenuClick());
        activity.setSupportActionBar(toolbar);
    }
}
