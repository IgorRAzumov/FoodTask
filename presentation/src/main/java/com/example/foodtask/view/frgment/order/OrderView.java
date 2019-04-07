package com.example.foodtask.view.frgment.order;

import com.arellomobile.mvp.MvpView;
import com.example.domain.model.OrderItem;
import com.example.foodtask.view.adapter.order.IOrderItemsPresenter;

public interface OrderView  extends MvpView {
    void setOrdersItemPresenter(IOrderItemsPresenter orderItemsPresenter);

    void orderItemAdded(OrderItem orderItem, int itemCount);

    void showErrorDataLoad();

    void clearOrderItems();
}
