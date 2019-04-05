package com.example.foodtask.view.adapter.products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

import butterknife.ButterKnife;

public class ProductViewHolder extends RecyclerView.ViewHolder implements IProductItem {
    private final ISingleItemClickListener clickListener;

    ProductViewHolder(@NonNull View itemView, ISingleItemClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.clickListener = clickListener;
    }

    @Override
    public void bindProductName(String name) {

    }

    @Override
    public void bindProductImage(String image) {

    }

    @Override
    public void bindProductCategory(int category) {

    }
}
