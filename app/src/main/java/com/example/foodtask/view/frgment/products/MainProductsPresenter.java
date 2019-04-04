package com.example.foodtask.view.frgment.products;

import com.arellomobile.mvp.InjectViewState;
import com.example.domain.interactor.counties.ILoadCountriesInteractor;
import com.example.domain.interactor.products.load.ILoadProductsInteractor;
import com.example.domain.model.Country;
import com.example.foodtask.utils.sheduler.ISchedulersProvider;
import com.example.foodtask.view.frgment.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class MainProductsPresenter extends BasePresenter<MainProductsView> {
    @Inject
    ILoadProductsInteractor loadProductsInteractor;
    @Inject
    ILoadCountriesInteractor loadCountriesInteractor;
    @Inject
    ISchedulersProvider schedulersProvider;

    private List<Country> countries;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    private void loadData() {
        addToDisposables(
                loadCountriesInteractor
                        .loadAllCountries()
                        .subscribeOn(schedulersProvider.io())
                        .observeOn(schedulersProvider.mainThread())
                        .observeOn(schedulersProvider.io())
                        .doOnSubscribe(disposable -> getViewState().showProgress())
                        .doOnSuccess(countries -> this.countries = countries)
                        .doFinally(() -> getViewState().hideProgress())
                        .subscribe(countries -> getViewState().countriesDataLoaded()
                                , throwable -> {
                                    Timber.e(throwable);
                                    getViewState().showErrorLoadData();
                                }));
    }
}
