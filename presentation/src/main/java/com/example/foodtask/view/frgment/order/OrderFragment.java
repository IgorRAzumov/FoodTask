package com.example.foodtask.view.frgment.order;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.domain.model.OrderItem;
import com.example.foodtask.R;
import com.example.foodtask.core.BaseFragment;
import com.example.foodtask.di.DI;
import com.example.foodtask.view.adapter.order.IOrderItemsPresenter;
import com.example.foodtask.view.adapter.order.OrderItemsAdapter;
import com.example.foodtask.view.utils.SpacingItemDecorator;

import java.math.BigDecimal;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import toothpick.Scope;
import toothpick.Toothpick;

import static com.example.foodtask.di.DI.PRODUCT_SCOPE;


public class OrderFragment extends BaseFragment implements OrderView {
    @BindView(R.id.ll_fr_order_empty_frame)
    LinearLayout emptyFrame;
    @BindView(R.id.ll_fr_order_info_frame)
    LinearLayout infoFrame;
    @BindView(R.id.rv_fr_order)
    RecyclerView orderItemsRecycler;
    @BindView(R.id.tv_tv_fr_order_sum)
    TextView orderSumText;
    @BindView(R.id.tv_fr_order_number)
    TextView orderNumberText;
    @BindView(R.id.tv_fr_order_currency)
    TextView orderCurrencyText;
    @BindView(R.id.bt_fr_order_pay)
    TextView orderPayButton;

    @BindColor(R.color.white)
    int whiteColor;
    @BindColor(R.color.gray_74)
    int gray74Color;

    @InjectPresenter
    OrderPresenter presenter;

    private OrderItemsAdapter orderItemsAdapter;
    private LinearLayoutManager orderRecyclerManager;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    public OrderFragment() {
    }

    @ProvidePresenter
    OrderPresenter providePresenter() {
        Scope scope = Toothpick.openScopes(DI.APP_SCOPE, PRODUCT_SCOPE);
        return scope.getInstance(OrderPresenter.class);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        orderPayButton.setOnClickListener(v -> presenter.onPayButtonClick());
    }

    @OnClick(R.id.bt_fr_order_order_clear)
    void onClearAllButtonClick() {
        presenter.onClearOrderClick();
    }

    @Override
    public void setOrdersItemPresenter(IOrderItemsPresenter orderItemsPresenter) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        Resources resources = getResources();

        orderItemsAdapter = new OrderItemsAdapter(orderItemsPresenter);
        orderItemsRecycler.setAdapter(orderItemsAdapter);
        orderItemsRecycler.addItemDecoration(new SpacingItemDecorator(
                resources.getInteger(R.integer.order_fr_orders_span_count),
                resources.getInteger(R.integer.default_recycler_spacing), false,
                resources.getDisplayMetrics().density));
        orderItemsRecycler.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void orderItemAdded(OrderItem orderItem, int position) {
        orderItemsAdapter.notifyItemInserted(position);
        orderRecyclerManager.scrollToPosition(position);
    }

    @Override
    public void showErrorDataLoad() {
        Toast.makeText(getContext(), getString(R.string.error_data_load), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void clearOrderItems() {
        orderItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideEmptyFrame() {
        emptyFrame.setVisibility(View.GONE);
        orderItemsRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTotalSum(BigDecimal sum) {
        orderSumText.setText(String.valueOf(sum));
    }

    @Override
    public void setOrderNumber(String dummyOrderNumber) {
        orderNumberText.setText(dummyOrderNumber);
    }

    @Override
    public void showInfoFrame() {
        infoFrame.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInfoFrame() {
        infoFrame.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyFrame() {
        emptyFrame.setVisibility(View.VISIBLE);
        orderItemsRecycler.setVisibility(View.GONE);
    }

    @Override
    public void clearSum() {
        orderSumText.setText(getString(R.string.common_zero));
    }

    @Override
    public void setEnablePayButton() {
        orderPayButton.setEnabled(true);
        orderPayButton.setTextColor(whiteColor);
    }

    @Override
    public void setDisablePayButton() {
        orderPayButton.setEnabled(false);
        orderPayButton.setTextColor(gray74Color);
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(getContext(), getString(R.string.order_payment_success), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setCurrency(String dummyCurrent) {
        orderCurrencyText.setText(dummyCurrent);
    }

    private void initRecycler() {
        orderItemsRecycler.setHasFixedSize(true);
        orderItemsRecycler.setLayoutManager(orderRecyclerManager = new LinearLayoutManager(getContext()));
    }
}
