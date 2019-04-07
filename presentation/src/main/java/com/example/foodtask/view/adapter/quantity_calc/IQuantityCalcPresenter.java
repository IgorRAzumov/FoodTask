package com.example.foodtask.view.adapter.quantity_calc;

import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

public interface IQuantityCalcPresenter extends ISingleItemClickListener {
    int getItemCount();

    void bindItem(IQuantityCalcItem calculatorViewHolder, int position);

    @Override
    void onItemClick(int position);
}
