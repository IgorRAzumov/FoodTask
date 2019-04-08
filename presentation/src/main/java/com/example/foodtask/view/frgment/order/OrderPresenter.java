package com.example.foodtask.view.frgment.order;

import com.arellomobile.mvp.InjectViewState;
import com.example.domain.interactor.calculator.ICalculateInteractor;
import com.example.domain.interactor.order.ICreateOrderInteractor;
import com.example.domain.model.OrderItem;
import com.example.foodtask.core.BasePresenter;
import com.example.foodtask.utils.sheduler.ISchedulersProvider;
import com.example.foodtask.view.adapter.order.IOrderItemView;
import com.example.foodtask.view.adapter.order.IOrderItemsPresenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import timber.log.Timber;

@InjectViewState
public final class OrderPresenter extends BasePresenter<OrderView> {
    private static final String DUMMY_ORDER_NUMBER = "#37";
    private static final String DUMMY_CURRENT_CURRENCY = "Р";

    @Inject
    ICreateOrderInteractor createOrderInteractor;

    @Inject
    ICalculateInteractor calculateInteractor;
    @Inject
    ISchedulersProvider schedulersProvider;

    private IOrderItemsPresenter orderItemsPresenter;
    private boolean visibleOrderItems;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setOrdersItemPresenter(getOrderItemsPresenter());
        startCreateOrder();
        getViewState().setCurrency(DUMMY_CURRENT_CURRENCY);
        getViewState().clearSum();
    }

    private IOrderItemsPresenter getOrderItemsPresenter() {
        if (orderItemsPresenter == null) {
            orderItemsPresenter = new IOrderItemsPresenter() {
                final List<OrderItem> orderItems = new ArrayList<>();

                @Override
                public int getItemCount() {
                    return orderItems.size();
                }

                @Override
                public void bind(IOrderItemView orderItemView, int position) {
                    OrderItem orderItem = orderItems.get(position);
                    orderItemView.setProductName(orderItem.getProductName());
                    orderItemView.setCurrency(orderItem.getCurrency());
                    orderItemView.setPricePerUnit(orderItem.getPricePerUnit());
                    orderItemView.setWeightUnit(orderItem.getProductWeightUnit());
                    orderItemView.setWeightUnitQuantity(orderItem.getWeightUnitQuantity());
                    orderItemView.setWeight(orderItem.getWeight());
                    orderItemView.setSum(orderItem.getSum());
                }

                @Override
                public void addOrderItem(OrderItem orderItem) {
                    orderItems.add(orderItem);
                }

                @Override
                public void clearAll() {
                    orderItems.clear();
                }

                @Override
                public List<OrderItem> getData() {
                    return orderItems;
                }

                @Override
                public void onItemClick(int position) {
                    Timber.d("order item click position %s", position);
                }
            };
        }
        return orderItemsPresenter;
    }

    private void startCreateOrder() {
        addToDisposables(
                createOrderInteractor
                        .ordersItemsObservable()
                        .subscribeOn(schedulersProvider.mainThread())
                        .doOnNext(orderItem -> orderItemsPresenter.addOrderItem(orderItem))
                        .doOnNext(orderItem -> getViewState().orderItemAdded(orderItem,
                                orderItemsPresenter.getItemCount()))
                        .observeOn(schedulersProvider.io())
                        .flatMap((Function<OrderItem, ObservableSource<BigDecimal>>) orderItem ->
                                calculateInteractor.calculateSum(orderItemsPresenter.getData()).toObservable())
                        .observeOn(schedulersProvider.mainThread())
                        .doOnNext(sum -> getViewState().setTotalSum(sum))
                        .filter(sum -> orderItemsPresenter.getItemCount() != 0 && !visibleOrderItems)
                        .subscribe(sum -> initNewOrder(), throwable -> {
                            Timber.e(throwable);
                            getViewState().showErrorDataLoad();
                        }));
    }

    private void initNewOrder() {
        getViewState().setOrderNumber(DUMMY_ORDER_NUMBER);
        getViewState().showInfoFrame();
        getViewState().hideEmptyFrame();
        getViewState().setEnablePayButton();
        visibleOrderItems = true;

    }

    void onClearOrderClick() {
        orderItemsPresenter.clearAll();
        getViewState().clearOrderItems();
        getViewState().hideInfoFrame();
        getViewState().showEmptyFrame();
        getViewState().setDisablePayButton();
        getViewState().clearSum();
        visibleOrderItems = false;
    }

    void onPayButtonClick() {
        getViewState().showSuccessMessage();
        onClearOrderClick();
    }
}
