package com.example.domain.interactor.order;

import com.example.domain.model.OrderItem;
import com.example.domain.model.Product;

import java.math.BigDecimal;

import io.reactivex.Observable;

public interface ICreateOrderInteractor {
    Observable<OrderItem> ordersItemsObservable();

    void finish();

    void orderItemAdded(Product product, float selectedWeight, BigDecimal sum);

}
