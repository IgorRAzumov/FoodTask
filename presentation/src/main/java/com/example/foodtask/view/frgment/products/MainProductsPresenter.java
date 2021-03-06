package com.example.foodtask.view.frgment.products;

import com.arellomobile.mvp.InjectViewState;
import com.example.domain.interactor.counties.ILoadCountriesInteractor;
import com.example.domain.interactor.products.load.ILoadProductsInteractor;
import com.example.foodtask.core.BasePresenter;
import com.example.foodtask.utils.sheduler.ISchedulersProvider;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public final class MainProductsPresenter extends BasePresenter<MainProductsView> {
    @Inject
    ILoadProductsInteractor loadProductsInteractor;
    @Inject
    ILoadCountriesInteractor loadCountriesInteractor;
    @Inject
    ISchedulersProvider schedulersProvider;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadData();
    }

    void onMenuClick() {
        getViewState().showMenu();
    }

    private void loadData() {
        addToDisposables(
                loadCountriesInteractor
                        .loadAllCountries()
                        .subscribeOn(schedulersProvider.io())
                        .observeOn(schedulersProvider.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showProgress())
                        .doAfterNext(countries -> getViewState().hideProgress())
                        .subscribe(countries -> getViewState().countriesDataLoaded(countries)
                                , throwable -> {
                                    Timber.e(throwable);
                                    getViewState().hideProgress();
                                    getViewState().showErrorLoadData();
                                }));
    }
}
