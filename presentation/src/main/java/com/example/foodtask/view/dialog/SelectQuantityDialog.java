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
    @BindView(R.id.tv_dg_select_quantity_currency)
    TextView currencyText;
    @BindView(R.id.tv_dg_select_quantity_weight_total)
    TextView totalWeightText;
    @BindView(R.id.tv_dg_select_quantity_weight_total_unit)
    TextView totalWeightUnitText;
    @BindView(R.id.tv_dg_select_quantity_weight_unit)
    TextView selectQuantityWeigthUnit;
    @BindView(R.id.tv_dg_select_quantity_weight)
    TextView weightText;
    @BindView(R.id.rv_dg_select_quantity_product)
    RecyclerView calcRecycler;

    private ISelectQuantityPresenter presenter;

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
        presenter.onViewAttached(this);
        return builder.create();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onViewDetach();
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
        weightText.setText(selectedQuantity);
    }

    @Override
    public void selectionCleared() {
        weightText.setText("0");
        totalWeightText.setText("0");
        sumText.setText("0");
    }

    @Override
    public void setTotal(String totalSum, String totalWeight) {
        weightText.setText(totalWeight);
        totalWeightText.setText(totalWeight);
        sumText.setText(totalSum);
    }

    @Override
    public void setProductName(String name) {
        productText.setText(name);
    }

    @Override
    public void setCurrency(String currency) {
        currencyText.setText(currency);
    }

    @Override
    public void setWeightUnit(String weightUnit) {
        totalWeightUnitText.setText(weightUnit);
        selectQuantityWeigthUnit.setText(weightUnit);
    }
}