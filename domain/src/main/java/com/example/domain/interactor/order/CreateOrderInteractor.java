package com.example.domain.interactor.order;

import com.example.domain.model.OrderItem;
import com.example.domain.model.Product;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public final class CreateOrderInteractor implements ICreateOrderInteractor {
    private Subject<OrderItem> orderItemSubject;

    @Override
    public Observable<OrderItem> ordersItemsObservable() {
        if (orderItemSubject == null) {
            orderItemSubject = PublishSubject.create();
        }
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
