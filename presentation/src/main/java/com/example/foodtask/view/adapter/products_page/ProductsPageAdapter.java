package com.example.foodtask.view.adapter.products_page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public final class ProductsPageAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList;
    private final List<String> fragmentTitleList;

    public ProductsPageAdapter(FragmentManager manager, List<Fragment> fragmentList,
                               List<String> fragmentTitleList) {
        super(manager);
        this.fragmentList = fragmentList;
        this.fragmentTitleList = fragmentTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
