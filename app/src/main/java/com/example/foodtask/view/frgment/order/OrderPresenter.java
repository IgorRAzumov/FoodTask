package com.example.foodtask.view.frgment.order;

import com.arellomobile.mvp.InjectViewState;
import com.example.domain.interactor.order.ICreateOrderInteractor;
import com.example.domain.model.OrderItem;
import com.example.foodtask.core.BasePresenter;
import com.example.foodtask.utils.sheduler.ISchedulersProvider;
import com.example.foodtask.view.adapter.order.IOrderItemView;
import com.example.foodtask.view.adapter.order.IOrderItemsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

@InjectViewState
public class OrderPresenter extends BasePresenter<OrderView> {
    @Inject
    ICreateOrderInteractor createOrderInteractor;
    @Inject
    ISchedulersProvider schedulersProvider;

    private IOrderItemsPresenter orderItemsPresenter;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setOrdersItemPresenter(getOrderItemsPresenter());
        startCreateOrder();
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
                public void onItemClick(int position) {

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
                        .subscribe(orderItem -> getViewState().orderItemAdded(orderItem,
                                orderItemsPresenter.getItemCount())
                                , throwable -> {
                                    Timber.e(throwable);
                                    getViewState().showErrorDataLoad();
                                }));
    }

    void onClearAllClick() {
        orderItemsPresenter.clearAll();
        getViewState().clearOrderItems();
    }
}
