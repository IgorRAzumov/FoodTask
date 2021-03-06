package com.example.foodtask.view.adapter.order;

import com.example.domain.model.OrderItem;
import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

import java.util.List;

public interface IOrderItemsPresenter extends ISingleItemClickListener {
    int getItemCount();

    void bind(IOrderItemView orderItem, int position);

    void addOrderItem(OrderItem orderItem);

    void clearAll();

    List<OrderItem> getData();
}
