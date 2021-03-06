package com.example.foodtask.view.dialog;

import com.example.domain.model.Product;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcItem;
import com.example.foodtask.view.adapter.quantity_calc.IQuantityCalcPresenter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public abstract class BaseSelectQuantityPresenter implements ISelectQuantityPresenter {
    private static final int MAX_DIGITS_AFTER_COMMA = 2;
    private static final Character ZERO_CHARACTER = '0';
    private static final char DOT_CHARACTER = '.';
    private static final Character CLEAR_CHARACTER = 'X';
    private static final Character COMMA_CHARACTER = ',';
    private static final Map<Integer, Character> CONTROLS = new HashMap<Integer, Character>() {{
        put(0, '1');
        put(1, '2');
        put(2, '3');
        put(3, '4');
        put(4, '5');
        put(5, '6');
        put(6, '7');
        put(7, '8');
        put(8, '9');
        put(9, COMMA_CHARACTER);
        put(10, ZERO_CHARACTER);
        put(11, CLEAR_CHARACTER);
    }};

    private final Product product;

    private int digitsAfterComma;
    private boolean commaAdded;
    private ISelectQuantityView selectQuantityView;
    private BigDecimal sum;
    private String selectedQuantity;

    protected BaseSelectQuantityPresenter(Product product) {
        this.product = product;
    }

    protected abstract void quantitySelected(float selectedWeight, BigDecimal sum);

    @Override
    public void onAbortClick() {
        selectQuantityView.exit();
    }


    @Override
    public void onViewAttached(ISelectQuantityView selectQuantityView) {
        this.selectQuantityView = selectQuantityView;
        this.selectQuantityView.showQuantitySelection(getQuantityCalcPresenter());
        this.selectQuantityView.setProductName(product.getName());
        this.selectQuantityView.setCurrency(product.getCurrency());
        this.selectQuantityView.setWeightUnit(product.getWeightUnit());
        selectedQuantity = ZERO_CHARACTER.toString();
    }

    @Override
    public void onAddButtonClick() {
        if (selectedQuantity.equals(ZERO_CHARACTER.toString())) {
            return;
        }
        quantitySelected(Float.parseFloat(selectedQuantity.replace(COMMA_CHARACTER, DOT_CHARACTER)), sum);
        selectQuantityView.exit();
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
                    addCharacterToDigitString(character);
                }
            }
        };
    }

    private void addCharacterToDigitString(Character character) {
        if (commaAdded) {
            addWhenCommaContains(character);
        } else {
            addDigitToQuantityResult(character);
        }

        calculateNewTotal();
    }

    private void addWhenCommaContains(Character character) {
        if (digitsAfterComma >= MAX_DIGITS_AFTER_COMMA) {
            return;
        }
        selectedQuantity += character;
        digitsAfterComma++;
    }


    private void calculateNewTotal() {
        BigDecimal sum = new BigDecimal(selectedQuantity.replace(COMMA_CHARACTER, '.'));
        this.sum = sum.multiply(product.getPrice());

        selectQuantityView.setTotal(String.format(Locale.getDefault(), "%.2f", this.sum),
                selectedQuantity);

    }

    private void addDigitToQuantityResult(Character character) {
        if (selectedQuantity.equals(ZERO_CHARACTER.toString())) {
            selectedQuantity = selectedQuantity.replace(ZERO_CHARACTER.toString(), "");
        }
        selectedQuantity += character;
    }

    private void addCommaToCalcResult(char character) {
        if (selectedQuantity.lastIndexOf(character) > 0) {
            return;
        }
        commaAdded = true;
        selectedQuantity += character;
        selectQuantityView.commaAdded(selectedQuantity);
    }

    private void clearCalcQuantityResult() {
        selectedQuantity = ZERO_CHARACTER.toString();
        commaAdded = false;
        selectQuantityView.selectionCleared();
    }
}
