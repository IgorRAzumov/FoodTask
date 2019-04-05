package com.example.foodtask.view.adapter.products;

import com.example.domain.model.Product;
import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

import java.util.List;

public interface IProductsPresenter extends ISingleItemClickListener {
    void bind(IProductItem productItem, int position);

    int getItemCount();

    void addData(List<Product> products);
}
