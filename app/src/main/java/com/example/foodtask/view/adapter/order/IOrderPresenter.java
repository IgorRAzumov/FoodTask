package com.example.foodtask.view.adapter.order;

import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

public interface IOrderPresenter extends ISingleItemClickListener {
    int getItemCount();

    void bind(IOrderItem orderItem, int position);
}
