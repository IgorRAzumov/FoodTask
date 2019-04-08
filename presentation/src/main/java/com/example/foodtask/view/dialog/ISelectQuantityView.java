package com.example.foodtask.view.dialog;

import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcPresenter;

interface ISelectQuantityView {
    void exit();

    void showQuantitySelection(IQuantityCalcPresenter quantityCalcPresenter);

    void commaAdded(String selectedQuantity);

    void selectionCleared();

    void setTotal(String totalSum, String totalWeight);

    void setProductName(String name);

    void setCurrency(String currency);

    void setWeightUnit(String weightUnit);
}
