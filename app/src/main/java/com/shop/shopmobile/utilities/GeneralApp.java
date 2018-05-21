package com.shop.shopmobile.utilities;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public class GeneralApp extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        injectViews();
    }

    private void injectViews() {
        ViewInjector.injectViews(this);
    }

}
