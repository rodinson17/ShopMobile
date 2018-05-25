package com.shop.shopmobile.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.shop.shopmobile.core.entities.Category;
import com.shop.shopmobile.fragments.PlaceHolderFragment;
import com.shop.shopmobile.interfaces.Observer;
import java.util.List;

public class MyAdapterViewPager extends FragmentPagerAdapter {

    private List<Category> listCategories;
    private Observer observer;

    public MyAdapterViewPager(FragmentManager fm, List<Category> listCategories, Observer observer) {
        super(fm);
        this.listCategories = listCategories;
        this.observer = observer;
    }

    @Override
    public Fragment getItem(int position) {
        PlaceHolderFragment phf = PlaceHolderFragment.getInstance(position);
        phf.setObserver(observer);
        return phf;
    }

    @Override
    public int getCount() { return listCategories.size(); }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) { return listCategories.get(position).getNameCategory(); }

}
