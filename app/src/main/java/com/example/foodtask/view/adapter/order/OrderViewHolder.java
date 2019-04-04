package com.example.foodtask.view.adapter.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.foodtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

final class OrderViewHolder extends RecyclerView.ViewHolder implements IOrderItem {
    @BindView(R.id.tv_it_order_product)
    TextView productText;
    @BindView(R.id.tv_it_order_sum)
    TextView sumText;
    @BindView(R.id.tv_it_order_sum_currency)
    TextView summCurrencyText;


    @BindView(R.id.tv_it_order_unit_count)
    TextView unitCountText;
    @BindView(R.id.tv_it_order_unit)
    TextView quantityUnitText;
    @BindView(R.id.tv_it_order_price_per_unit)
    TextView pricePerUnitText;
    @BindView(R.id.tv_it_order_currency)
    TextView unitCurrencyText;

    @BindView(R.id.tv_it_order_order_unit_count)
    TextView orderUnitCountText;
    @BindView(R.id.tv_it_order_order_unit)
    TextView orderQuantityUnitText;
    @BindView(R.id.tv_it_order_order_price_per_unit)
    TextView orderPricePerUnitText;
    @BindView(R.id.tv_it_order_order_currency)
    TextView orderUnitCurrencyText;

    OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
