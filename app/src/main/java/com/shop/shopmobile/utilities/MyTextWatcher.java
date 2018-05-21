package com.shop.shopmobile.utilities;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class MyTextWatcher implements TextWatcher {

    private TextInputLayout til;
    private TextView tvMsg;

    public MyTextWatcher(TextView tvMsg) {
        this.tvMsg = tvMsg;
    }

    public MyTextWatcher(TextInputLayout til) {
        this.til = til;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (til == null) {
            if (editable.length() > 0) {
                tvMsg.setText(Constant.EMPTY_STRING);
            }
        } else {
            til.setErrorEnabled(false);
        }
    }
}
