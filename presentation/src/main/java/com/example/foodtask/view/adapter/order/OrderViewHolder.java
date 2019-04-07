package com.example.foodtask.view.adapter.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.foodtask.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

final class OrderViewHolder extends RecyclerView.ViewHolder implements IOrderItemView {
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

    @BindView(R.id.tv_it_order_total_weight)
    TextView orderTotalWeightText;
    @BindView(R.id.tv_it_order_order_unit)
    TextView orderQuantityUnitText;
    @BindView(R.id.tv_it_order_order_price_per_unit)
    TextView orderPricePerUnitText;
    @BindView(R.id.tv_it_order_order_currency)
    TextView orderUnitCurrencyText;

    @BindInt(R.integer.digits_after_comma)
    int digitsAfterComma;

    OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setProductName(String productName) {
        productText.setText(productName);
    }

    @Override
    public void setWeightUnit(String productWeightUnit) {
        quantityUnitText.setText(productWeightUnit);
        orderQuantityUnitText.setText(productWeightUnit);
    }

    @Override
    public void setCurrency(String currency) {
        summCurrencyText.setText(currency);
        unitCurrencyText.setText(currency);
        orderUnitCurrencyText.setText(currency);
    }

    @Override
    public void setWeightUnitQuantity(int weightUnitQuantity) {
        unitCountText.setText(String.valueOf(weightUnitQuantity));
    }

    @Override
    public void setPricePerUnit(BigDecimal pricePerUnit) {
        String price = pricePerUnit.setScale(digitsAfterComma, RoundingMode.HALF_UP).toString();
        pricePerUnitText.setText(price);
        orderPricePerUnitText.setText(price);
    }

    @Override
    public void setWeight(float weight) {
        orderTotalWeightText.setText(String.valueOf(weight));
    }

    @Override
    public void setSum(BigDecimal sum) {
        String price = sum.setScale(digitsAfterComma, RoundingMode.HALF_UP).toString();
        sumText.setText(price);
    }
}
