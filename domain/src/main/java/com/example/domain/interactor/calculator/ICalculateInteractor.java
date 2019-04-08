package com.example.domain.interactor.calculator;

import com.example.domain.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

import io.reactivex.Single;

public interface ICalculateInteractor {

    Single<BigDecimal> calculateSum(List<OrderItem> data);
}
