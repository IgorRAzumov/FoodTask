package com.example.foodtask.core;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.foodtask.R;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends MvpAppCompatFragment {
    private Unbinder unbinder;
    private IFragmentContainer fragmentContainer;

    public BaseFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentContainer) {
            fragmentContainer = (IFragmentContainer) context;
        } else {
            throw new RuntimeException(String.format("%s %s", context.toString(),
                    getString(R.string.error_implement_fragment_container)));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentContainer = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected IFragmentContainer getFragmentContainer() {
        return fragmentContainer;
    }
}
