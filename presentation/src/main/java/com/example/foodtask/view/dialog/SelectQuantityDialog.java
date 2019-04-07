package com.example.foodtask.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.foodtask.R;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcPresenter;
import com.example.foodtask.view.adapter.quantity_calc.QuantityCalcAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import timber.log.Timber;

public class SelectQuantityDialog extends DialogFragment implements ISelectQuantityView {
    public static final String TAG = SelectQuantityDialog.class.toString();

    @BindView(R.id.tv_dg_select_quantity_product)
    TextView productText;
    @BindView(R.id.tv_dg_select_quantity_sum)
    TextView sumText;
    @BindView(R.id.tv_dg_select_quantity_weight_total)
    TextView totalWeigthText;
    @BindView(R.id.tv_dg_select_quantity_weight)
    TextView weigthText;
    @BindView(R.id.rv_dg_select_quantity_product)
    RecyclerView calcRecycler;

    private Unbinder unbinder;

    public static SelectQuantityDialog newInstance(ISelectQuantityPresenter presenter) {
        SelectQuantityDialog selectQuantityDialog = new SelectQuantityDialog();
        selectQuantityDialog.presenter = presenter;
        return selectQuantityDialog;
    }

    @OnClick(R.id.bt_dg_select_quantity_abort)
    void onAbortButtonClick() {
        presenter.onAbortClick();
    }

    @OnClick(R.id.bt_dg_select_quantity_add)
    void onAddButtonClick() {
        presenter.onAddButtonClick();
    }

    private ISelectQuantityPresenter presenter;
    private QuantityCalcAdapter adapter;

    @NotNull
    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        if (activity == null) {
            Timber.e("activity is null in %s", this.toString());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(activity));
        View view = activity.getLayoutInflater().inflate(R.layout.dialog_select_quantity, null);
        unbinder = ButterKnife.bind(this, view);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewAttached(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void exit() {
        dismiss();
    }

    @Override
    public void showQuantitySelection(IQuantityCalcPresenter quantityCalcPresenter) {
        calcRecycler.setHasFixedSize(true);
        calcRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
        calcRecycler.setAdapter(new QuantityCalcAdapter(quantityCalcPresenter));

    }

    @Override
    public void commaAdded(String selectedQuantity) {
        weigthText.setText(selectedQuantity);
    }

    @Override
    public void selectionCleared() {
        weigthText.setText("0");
        totalWeigthText.setText("0");
        sumText.setText("0");
    }

    @Override
    public void setTotal(String totalSum, String totalWeight) {
        weigthText.setText(totalWeight);
        totalWeigthText.setText(totalWeight);
        sumText.setText(totalSum);
    }
}