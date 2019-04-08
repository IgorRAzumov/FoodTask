package com.example.domain.interactor.calculator;

import com.example.domain.model.OrderItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import io.reactivex.Single;

public class CalculateInteractor implements ICalculateInteractor {
    @Override
    public Single<BigDecimal> calculateSum(List<OrderItem> data) {
        return Single.just(calculate(data));
    }

    private BigDecimal calculate(List<OrderItem> data) {
        BigDecimal sum = new BigDecimal(0);
        for (OrderItem orderItem : data) {
            sum = sum.add(orderItem.getSum());
        }

        return sum.setScale(2, RoundingMode.HALF_UP);
    }
}
