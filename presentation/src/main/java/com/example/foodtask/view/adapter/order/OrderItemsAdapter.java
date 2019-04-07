package com.example.foodtask.view.adapter.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.foodtask.R;

public final class OrderItemsAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private final IOrderItemsPresenter orderPresenter;

    public OrderItemsAdapter(IOrderItemsPresenter orderPresenter) {
        this.orderPresenter = orderPresenter;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_order, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder orderViewHolder, int i) {
        orderPresenter.bind(orderViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return orderPresenter.getItemCount();
    }
}
