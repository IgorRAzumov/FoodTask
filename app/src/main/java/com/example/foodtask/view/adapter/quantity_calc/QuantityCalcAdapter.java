package com.example.foodtask.view.adapter.quantity_calc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.foodtask.R;

public class QuantityCalcAdapter extends RecyclerView.Adapter<QuantityCalcViewHolder> {
    private final IQuantityCalcPresenter presenter;

    public QuantityCalcAdapter(IQuantityCalcPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public QuantityCalcViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuantityCalcViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_calc_control, viewGroup, false), presenter);
    }

    @Override
    public void onBindViewHolder(@NonNull QuantityCalcViewHolder calculatorViewHolder, int i) {
        presenter.bindItem(calculatorViewHolder,i);
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }
}
