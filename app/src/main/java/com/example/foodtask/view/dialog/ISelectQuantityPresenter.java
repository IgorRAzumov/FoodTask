package com.example.foodtask.view.dialog;

public interface ISelectQuantityPresenter {
    void onAbortClick();

    void onAddButtonClick();

    void onViewCreated(ISelectQuantityView selectQuantityView);

    void onViewDetach();
}
