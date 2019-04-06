package com.example.domain.interactor.order;

import com.example.domain.model.OrderItem;
import com.example.domain.model.Product;

import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;

public class CreateOrderInteractor implements ICreateOrderInteractor {
    private final ReplaySubject<OrderItem> orderItemSubject;

    public CreateOrderInteractor() {
        orderItemSubject = ReplaySubject.create();
    }


    @Override
    public Observable<OrderItem> ordersItemsObservable() {
        return orderItemSubject;
    }

    @Override
    public void orderItemAdded(Product product, float selectedWeight, double sum) {
        orderItemSubject.onNext(new OrderItem(selectedWeight, sum, product));
    }

    @Override
    public void finish() {
        orderItemSubject.onComplete();
    }
}
