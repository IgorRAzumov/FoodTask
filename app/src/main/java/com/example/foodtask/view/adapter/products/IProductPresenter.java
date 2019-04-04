package com.example.foodtask.view.adapter.products;

import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

public interface IProductPresenter extends ISingleItemClickListener {
    void bind(IProductItem productItem, int position);

    int getItemCount();

}
