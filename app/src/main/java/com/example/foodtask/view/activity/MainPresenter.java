package com.example.foodtask.view.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcItem;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcPresenter;
import com.example.foodtask.view.dialog.ISelectQuantityPresenter;
import com.example.foodtask.view.dialog.ISelectQuantityView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showProductsScreen();
        getViewState().showOrderScreen();
        getViewState().showCalcDialog(getSelectQuantityPresenter());
    }

    private ISelectQuantityPresenter getSelectQuantityPresenter() {
        return new ISelectQuantityPresenter() {
            final Character CLEAR_CHARACTER = 'X';
            final Character COMMA_CHARACTER = ',';
            final Map<Integer, Character> quantityControls = new HashMap<Integer, Character>() {{
                put(0, '1');
                put(1, '2');
                put(2, '3');
                put(3, '4');
                put(4, '5');
                put(5, '6');
                put(6, '7');
                put(7, '8');
                put(8, '9');
                put(9, ',');
                put(10, '0');
                put(11, 'X');
            }};

            ISelectQuantityView selectQuantityView;
            String selectedQuantity;
            double pricePerKg = 3;

            @Override
            public void onAbortClick() {
                selectQuantityView.exit();
            }

            @Override
            public void onAddButtonClick() {

            }

            @Override
            public void onViewCreated(ISelectQuantityView selectQuantityView) {
                this.selectQuantityView = selectQuantityView;
                this.selectQuantityView.showQuantitySelection(getQuantityCalcPresenter());
                selectedQuantity = "0";
            }

            @Override
            public void onViewDetach() {
                selectQuantityView = null;
            }


            private void calculateNewTotal() {
                double sum = Double.parseDouble(selectedQuantity.replace(COMMA_CHARACTER,'.'));
                selectQuantityView.setTotal(sum * pricePerKg, selectedQuantity);
            }

            private void addDigitToQuantityResult(Character character) {
                selectedQuantity += character;
            }

            private void addCommaToCalcResult(char character) {
                if (selectedQuantity.lastIndexOf(character) > 0) {
                    return;
                }

                selectedQuantity += character;
                selectQuantityView.commaAdded(selectedQuantity);
            }

            private void clearCalcQuantityResult() {
                selectedQuantity = "0";
                selectQuantityView.selectionCleared();
            }

            private IQuantityCalcPresenter getQuantityCalcPresenter() {
                return new IQuantityCalcPresenter() {

                    @Override
                    public int getItemCount() {
                        return quantityControls.size();
                    }

                    @Override
                    public void bindItem(IQuantityCalcItem calculatorViewHolder, int position) {
                        calculatorViewHolder.setControlValue(Objects.requireNonNull(
                                quantityControls.get(position)).toString());
                    }

                    @Override
                    public void onItemClick(int position) {
                        Character character = quantityControls.get(position);
                        if (character == null) {
                            throw new RuntimeException(String.format("unknown character in" +
                                    " calcQuantity in%s", this.getClass().toString()));
                        }

                        if (character.equals(CLEAR_CHARACTER)) {
                            clearCalcQuantityResult();
                        } else if (character.equals(COMMA_CHARACTER)) {
                            addCommaToCalcResult(character);
                        } else {
                            addDigitToQuantityResult(character);
                            calculateNewTotal();
                        }
                    }
                };
            }
        };
    }
}
