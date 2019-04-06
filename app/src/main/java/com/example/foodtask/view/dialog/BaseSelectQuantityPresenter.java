package com.example.foodtask.view.dialog;

import com.example.domain.model.Product;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcItem;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseSelectQuantityPresenter implements ISelectQuantityPresenter {
    private final Character CLEAR_CHARACTER = 'X';
    private final Character COMMA_CHARACTER = ',';
    private final Map<Integer, Character> CONTROLS = new HashMap<Integer, Character>() {{
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

    private final Product product;

    private ISelectQuantityView selectQuantityView;
    private float selectedWeight;
    private double sum;
    private String selectedQuantity;

    protected BaseSelectQuantityPresenter(Product product) {
        this.product = product;
    }

    protected abstract void quantitySelected(float selectedWeight, double sum);

    @Override
    public void onAbortClick() {
        selectQuantityView.exit();
    }


    @Override
    public void onViewAttached(ISelectQuantityView selectQuantityView) {
        this.selectQuantityView = selectQuantityView;
        this.selectQuantityView.showQuantitySelection(getQuantityCalcPresenter());
        selectedQuantity = "0";
    }

    @Override
    public void onAddButtonClick() {
        quantitySelected(selectedWeight,sum);
    }

    @Override
    public void onViewDetach() {
        selectQuantityView = null;
    }

    private IQuantityCalcPresenter getQuantityCalcPresenter() {
        return new IQuantityCalcPresenter() {
            @Override
            public int getItemCount() {
                return CONTROLS.size();
            }

            @Override
            public void bindItem(IQuantityCalcItem calculatorViewHolder, int position) {
                calculatorViewHolder.setControlValue(Objects.requireNonNull(
                        CONTROLS.get(position)).toString());
            }

            @Override
            public void onItemClick(int position) {
                Character character = CONTROLS.get(position);
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


    private void calculateNewTotal() {
        double sum = Double.parseDouble(selectedQuantity.replace(COMMA_CHARACTER, '.'));
        selectQuantityView.setTotal(sum * product.getPrice(), selectedQuantity);
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
}
