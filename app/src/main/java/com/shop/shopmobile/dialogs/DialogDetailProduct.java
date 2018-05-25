package com.shop.shopmobile.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.shop.shopmobile.R;
import com.shop.shopmobile.core.entities.Product;
import com.shop.shopmobile.interfaces.Observer;
import com.shop.shopmobile.utilities.Constant;
import com.squareup.picasso.Picasso;

public class DialogDetailProduct extends Dialog {

    private ImageView ivImageProduct;
    private ImageView ivRemoveProduct;
    private ImageView ivAddProduct;
    private TextView tvNameProduct;
    private TextView tvPriceProduct;
    private TextView tvDetailProduct;
    private EditText etQuantity;
    private Button btnCancel;
    private Button btnOk;

    private Product product;
    private int quantity = 0;
    private Observer observer;


    public DialogDetailProduct(@NonNull Context context, Product product, Observer observer) {
        super(context);
        this.product = product;
        this.observer = observer;
        init();
    }

    public DialogDetailProduct(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private void init() {
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_detail_product);
        //getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ivImageProduct = findViewById(R.id.iv_image_d);
        ivRemoveProduct = findViewById(R.id.iv_remove);
        ivAddProduct = findViewById(R.id.iv_add);
        tvNameProduct = findViewById(R.id.tv_name_d);
        tvPriceProduct = findViewById(R.id.tv_price_d);
        tvDetailProduct = findViewById(R.id.tv_detail_d);
        etQuantity = findViewById(R.id.et_quantity_d);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_ok);

        setInfoView();

        ivRemoveProduct.setOnClickListener(this::onClickRemove);
        ivAddProduct.setOnClickListener(this::onClickAdd);
        btnCancel.setOnClickListener(view -> { dismiss(); });
        btnOk.setOnClickListener(this::onClickOK);

        etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() > 0) {
                    quantity = Integer.valueOf(editable.toString());
                } else {
                    quantity = 0;
                }
            }
        });
    }

    private void setInfoView() {

        Picasso.get().load(product.getImageProduct()).into(ivImageProduct);
        tvNameProduct.setText(product.getNameProduct());
        tvPriceProduct.setText("$ "+product.getPriceProduct());
        tvDetailProduct.setText(product.getDescription());
//        if (product.getCantOrder() > 0) {
//            etQuantity.setText(product.getQuantity() + Constant.EMPTY_STRING);
//            quantity = product.getQuantity();
//        }
    }

    private void onClickRemove(View v) {
        quantity--;
        if (quantity < 0) {
            quantity = 0;
            etQuantity.setText(quantity + "");
        } else {
            etQuantity.setText(quantity + "");
        }
    }

    private void onClickAdd(View v) {
        quantity++;
        etQuantity.setText(quantity + "");
    }

    private void onClickOK(View v) {
        observer.addProductDetailCart(product, quantity);
        dismiss();
    }

}
