package com.example.foodtask.view.adapter.products;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodtask.R;
import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewHolder extends RecyclerView.ViewHolder implements IProductItem {
    @BindView(R.id.iv_it_product_image)
    ImageView productImage;
    @BindView(R.id.tv_it_product_name)
    TextView productNameText;
    @BindView(R.id.vi_category_border)
    View categoryBorderView;

    @BindColor(R.color.color_blue_selected)
    int firstCategoryColor;
    @BindColor(R.color.color_yellow_selected)
    int secondCategoryColor;

    private final ISingleItemClickListener clickListener;

    ProductViewHolder(@NonNull View itemView, ISingleItemClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.clickListener = clickListener;
        itemView.setOnClickListener(v -> onItemClick());
    }

    private void onItemClick() {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            clickListener.onItemClick(position);
        }
    }

    @Override
    public void bindProductName(String name) {
        productNameText.setText(name);
    }

    @Override
    public void bindProductImage(String image) {

    }

    @Override
    public void bindProductCategory(int category) {
        if (category == 1) {
            categoryBorderView.setBackgroundColor(firstCategoryColor);
        } else {
            categoryBorderView.setBackgroundColor(secondCategoryColor);
        }
    }
}
