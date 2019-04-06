package com.example.foodtask.view.adapter.products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.foodtask.R;
import com.example.foodtask.utils.image.loader.IImageLoader;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private final IProductsPresenter presenter;
    private final IImageLoader imageLoader;

    public ProductAdapter(IProductsPresenter presenter, IImageLoader imageLoader) {
        this.presenter = presenter;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false), presenter, imageLoader);
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
