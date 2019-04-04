package com.example.foodtask.view.adapter.products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.foodtask.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private final IProductPresenter presenter;

    public ProductAdapter(IProductPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product,viewGroup,false), presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        presenter.bind(productViewHolder, i);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }
}
