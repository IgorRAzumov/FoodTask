package com.example.foodtask.view.adapter.quantity_calc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.foodtask.R;
import com.example.foodtask.view.adapter.common.ISingleItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

final class QuantityCalcViewHolder extends RecyclerView.ViewHolder implements IQuantityCalcItem {
    @BindView(R.id.bt_it_calc_control)
    Button controlButton;

    private final ISingleItemClickListener clickListener;

    QuantityCalcViewHolder(@NonNull View itemView, ISingleItemClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.clickListener = clickListener;
        controlButton.setOnClickListener(v -> onItemClick());
    }

    @Override
    public void setControlValue(String character) {
        controlButton.setText(character);
    }

    private void onItemClick() {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            clickListener.onItemClick(position);
        }
    }
}
