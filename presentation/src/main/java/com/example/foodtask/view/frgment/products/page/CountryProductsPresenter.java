package com.example.foodtask.view.frgment.products.page;

import com.arellomobile.mvp.InjectViewState;
import com.example.domain.interactor.order.ICreateOrderInteractor;
import com.example.domain.interactor.products.load.ILoadProductsInteractor;
import com.example.domain.model.Country;
import com.example.domain.model.Product;
import com.example.foodtask.core.BasePresenter;
import com.example.foodtask.utils.sheduler.ISchedulersProvider;
import com.example.foodtask.view.adapter.products.IProductItem;
import com.example.foodtask.view.adapter.products.IProductsPresenter;
import com.example.foodtask.view.dialog.BaseSelectQuantityPresenter;
import com.example.foodtask.view.dialog.ISelectQuantityPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import timber.log.Timber;

@InjectViewState
public class CountryProductsPresenter extends BasePresenter<CountryProductsView> {
    @Inject
    ILoadProductsInteractor loadProductsInteractor;
    @Inject
    ICreateOrderInteractor createOrderInteractor;
    @Inject
    ISchedulersProvider schedulersProvider;

    private IProductsPresenter productsPresenter;

    private Country country;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setProductsListPresenter(getProductsPresenter());

        if (country == null) {
            loadProducts(loadAllProducts());
        } else {
            loadProducts(loadProductsByCountry());
        }
    }

    void setCountry(Country country) {
        this.country = country;
    }

    private void loadProducts(Flowable<List<Product>> productsFlowable) {
        addToDisposables(
                productsFlowable
                        .subscribeOn(schedulersProvider.io())
                        .observeOn(schedulersProvider.mainThread())
                        .doOnSubscribe(disposable -> getViewState().showProgress())
                        .doOnNext(products -> getViewState().hideProgress())
                        .subscribe(products -> {
                            productsPresenter.addData(products);
                            getViewState().productsLoaded();
                        }, throwable -> {
                            Timber.e(throwable);
                            getViewState().hideProgress();
                            getViewState().showErrorLoadData();
                        })
        );
    }

    private Flowable<List<Product>> loadProductsByCountry() {
        return loadProductsInteractor.loadProductsByCountry(country.getId());
    }

    private Flowable<List<Product>> loadAllProducts() {
        return loadProductsInteractor.loadAllProducts();
    }

    private IProductsPresenter getProductsPresenter() {
        if (productsPresenter == null) {
            return productsPresenter = new IProductsPresenter() {
                final List<Product> products = new ArrayList<>();

                @Override
                public void bind(IProductItem productItem, int position) {
                    Product product = products.get(position);
                    productItem.bindProductName(product.getName());
                    productItem.bindProductImage(product.getImage());
                    productItem.bindProductCategory(product.getCategory());
                }

                @Override
                public int getItemCount() {
                    return products.size();
                }

                @Override
                public void addData(List<Product> products) {
                    this.products.addAll(products);
                }

                @Override
                public void onItemClick(int position) {
                    Product product = products.get(position);
                    getViewState().showSelectQuantityDialog(getSelectQuantityPresenter(product),
                            product);
                }
            };
        }
        return productsPresenter;
    }

    private ISelectQuantityPresenter getSelectQuantityPresenter(Product product) {
        return new BaseSelectQuantityPresenter(product) {
            @Override
            protected void quantitySelected(float selectedWeight, BigDecimal sum) {
                createOrderInteractor.orderItemAdded(product,selectedWeight,sum);
            }
        };
    }
}
