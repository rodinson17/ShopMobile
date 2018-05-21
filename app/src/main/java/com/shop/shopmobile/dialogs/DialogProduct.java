package com.shop.shopmobile.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.shop.shopmobile.R;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.entities.Product;

public class DialogProduct extends Dialog {

    // region Attributes
    private TextView tvCancel;
    private TextView tvTitle;
    private TextView tvAction;
    private TextView tvCategoryProduct;
    private TextView tvDelete;
    private ImageView ivImageProduct;
    private TextInputLayout tilNameProduct;
    private TextInputLayout tilpriceProduct;
    private EditText etNameProduct;
    private EditText etPriceProduct;

    private Product product;
    private boolean update;
    // endregion

    public DialogProduct(@NonNull Context context, Product product) {
        super(context);
        this.product = product;
        init();
    }

    public DialogProduct(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    private void init() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setContentView(R.layout.dialog_products);
        //getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

         tvCancel = findViewById(R.id.tv_cancel_dp);
         tvTitle = findViewById(R.id.tv_title_dp);
         tvAction = findViewById(R.id.tv_action_dp);
         tvCategoryProduct = findViewById(R.id.tv_category_product_dp);
         tvDelete = findViewById(R.id.tv_delete_product);
         ivImageProduct = findViewById(R.id.iv_image_product);
         tilNameProduct = findViewById(R.id.til_name_product);
         tilpriceProduct = findViewById(R.id.til_price_product);
         etNameProduct = findViewById(R.id.et_name_product);
         etPriceProduct = findViewById(R.id.et_price_product);

        System.out.println("prod: "+product.toString());
        if (product.getNameProduct() == null){

            tvTitle.setText("Add Product");
            tvAction.setText("Save");
            tvDelete.setVisibility(View.GONE);
            tvCategoryProduct.setText("Category Product: " + product.getCategory().getNameCategory());
            setEnabledView();
        } else {

            tvTitle.setText("Info Product");
            tvAction.setText("Update");
            tvDelete.setVisibility(View.VISIBLE);
            setDisabledView();

            etNameProduct.setText(product.getNameProduct());
            etPriceProduct.setText(product.getPriceProduct() + "");
            tvCategoryProduct.setText("Category Product: " + product.getCategory().getNameCategory());
        }

        tvCancel.setOnClickListener(view -> { dismiss(); });
        tvAction.setOnClickListener(this::onClickAction);
        tvDelete.setOnClickListener(this::onClickDelete);
    }

    public void onClickAction(View v) {

        if (product.getNameProduct() == null) {

            // falta Validar campos

            product.setCode("001");
            product.setNameProduct("Leche");
            product.setDescription("Leche por 1 lt");
            product.setPriceProduct(1500);
            product.setImageProduct(R.drawable.imagen_product);
            product.setQuantity(10);
            System.out.println("guardar "+product.toString());

            AppClientRealm.insertSingleData(product);
            dismiss();
        } else {

            if (update) {
                tvAction.setText("update");
                update = false;
                setDisabledView();
                System.out.println("guardar");
            } else {
                 tvAction.setText("Save");
                 update = true;
                 setEnabledView();
            }
        }
    }

    public void onClickDelete(View v) {
        System.out.println("delete");
    }
   

    private void setDisabledView() {
        etNameProduct.setEnabled(false);
        etPriceProduct.setEnabled(false);
    }

    private void setEnabledView() {
        etNameProduct.setEnabled(true);
        etPriceProduct.setEnabled(true);
    }

}
