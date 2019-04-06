package com.example.foodtask.view.frgment.order;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.domain.model.OrderItem;
import com.example.foodtask.R;
import com.example.foodtask.core.BaseFragment;
import com.example.foodtask.view.adapter.order.IOrderItemsPresenter;
import com.example.foodtask.view.adapter.order.OrderItemsAdapter;

import butterknife.BindView;
import butterknife.OnClick;


public class OrderFragment extends BaseFragment implements OrderView {
    @BindView(R.id.rv_fr_order)
    RecyclerView orderItemsRecycler;

    @InjectPresenter
    OrderPresenter presenter;

    private OrderItemsAdapter orderItemsAdapter;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public OrderFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
    }

    @OnClick(R.id.bt_fr_order_number_clear)
    void onClearAllButtonClick(){
        presenter.onClearAllClick();
    }

    @Override
    public void setOrdersItemPresenter(IOrderItemsPresenter orderItemsPresenter) {
        orderItemsAdapter = new OrderItemsAdapter(orderItemsPresenter);
        orderItemsRecycler.setAdapter(orderItemsAdapter);
    }

    @Override
    public void orderItemAdded(OrderItem orderItem, int itemCount) {
        orderItemsAdapter.notifyItemInserted(itemCount);
    }

    @Override
    public void showErrorDataLoad() {
        Toast.makeText(getContext(), getString(R.string.error_data_load), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void clearOrderItems() {
        orderItemsAdapter.notifyDataSetChanged();
    }

    private void initRecycler() {
        orderItemsRecycler.setHasFixedSize(true);
        orderItemsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
