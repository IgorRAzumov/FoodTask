package com.example.foodtask.view.adapter.order;

import java.math.BigDecimal;

public interface IOrderItemView {
    void setProductName(String productName);

    void setWeightUnit(String productWeightUnit);

    void setCurrency(String currency);

    void setWeightUnitQuantity(int weightUnitQuantity);

    void setPricePerUnit(BigDecimal pricePerUnit);

    void setWeight(float weight);

    void setSum(BigDecimal sum);
}
