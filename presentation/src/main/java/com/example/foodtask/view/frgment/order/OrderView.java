package com.example.foodtask.view.frgment.order;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.domain.model.OrderItem;
import com.example.foodtask.view.adapter.order.IOrderItemsPresenter;

import java.math.BigDecimal;

interface OrderView  extends MvpView {
    void setOrdersItemPresenter(IOrderItemsPresenter orderItemsPresenter);

    void orderItemAdded(OrderItem orderItem, int itemCount);

    void showErrorDataLoad();

    void clearOrderItems();

    void hideEmptyFrame();

    void setTotalSum(BigDecimal sum);

    void setOrderNumber(String dummyOrderNumber);

    void showInfoFrame();

    void hideInfoFrame();

    void showEmptyFrame();

    void clearSum();

    void setEnablePayButton();

    void setDisablePayButton();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessMessage();

    void setCurrency(String dummyCurrentCurrency);
}
