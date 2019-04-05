package com.example.foodtask.view.adapter.products;

public interface IProductItem {
    void bindProductName(String name);

    void bindProductImage(String image);

    void bindProductCategory(int category);
}
