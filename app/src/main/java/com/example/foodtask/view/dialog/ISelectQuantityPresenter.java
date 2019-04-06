package com.example.foodtask.view.dialog;

public interface ISelectQuantityPresenter {
    void onAbortClick();

    void onAddButtonClick();

    void onViewAttached(ISelectQuantityView selectQuantityView);

    void onViewDetach();
}
