package com.shop.shopmobile.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;
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
    private TextInputLayout tilCodeProduct;
    private TextInputLayout tilNameProduct;
    private TextInputLayout tilPriceProduct;
    private TextInputLayout tilQuantityProduct;
    private TextInputLayout tilDescriptionProduct;
    private EditText etCodeProduct;
    private EditText etNameProduct;
    private EditText etPriceProduct;
    private EditText etQuantityProduct;
    private EditText etDescriptionProduct;

    private DialogProductListener listener;
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
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // TODO: revisar los estilos de las vistas
         tvCancel = findViewById(R.id.tv_cancel_dp);
         tvTitle = findViewById(R.id.tv_title_dp);
         tvAction = findViewById(R.id.tv_action_dp);
         tvCategoryProduct = findViewById(R.id.tv_category_product_dp);
         tvDelete = findViewById(R.id.tv_delete_product);
         ivImageProduct = findViewById(R.id.iv_image_product);
         tilCodeProduct = findViewById(R.id.til_code_product);
         tilNameProduct = findViewById(R.id.til_name_product);
         tilPriceProduct = findViewById(R.id.til_price_product);
         tilQuantityProduct = findViewById(R.id.til_quantity_product);
         tilDescriptionProduct = findViewById(R.id.til_description_product);
         etCodeProduct = findViewById(R.id.et_code_product);
         etNameProduct = findViewById(R.id.et_name_product);
         etPriceProduct = findViewById(R.id.et_price_product);
         etQuantityProduct = findViewById(R.id.et_quantity_product);
         etDescriptionProduct = findViewById(R.id.et_description_product);

        System.out.println("prod: "+product.toString());
        if (product.getNameProduct() == null){

            tvTitle.setText("Add Product");
            tvAction.setText("Save");
            tvDelete.setVisibility(View.INVISIBLE);
            tvCategoryProduct.setText("Category Product: " + product.getCategory().getNameCategory());
            setEnabledView(true);
        } else {

            tvTitle.setText("Info Product");
            tvAction.setText("Update");
            tvDelete.setVisibility(View.VISIBLE);
            setEnabledView(false);

            etCodeProduct.setText(product.getCode());
            etNameProduct.setText(product.getNameProduct());
            etPriceProduct.setText(product.getPriceProduct() + "");
            etQuantityProduct.setText(product.getQuantity() + "");
            etDescriptionProduct.setText(product.getDescription());
            tvCategoryProduct.setText("Category Product: " + product.getCategory().getNameCategory());
        }

        tvCancel.setOnClickListener(view -> { dismiss(); });
        tvAction.setOnClickListener(this::onClickAction);
        tvDelete.setOnClickListener(this::onClickDelete);
    }

    public void onClickAction(View v) {

        if (product.getNameProduct() == null) {

            insertProduct();
            dismiss();
        } else {

            if (update) {

                tvAction.setText("update");
                update = false;
                setEnabledView(false);
                updateproduct();
            } else {

                tvAction.setText("Save");
                update = true;
                setEnabledView(true);
            }
        }
    }

    private void insertProduct(){
        //TODO: falta Validar campos

        // al momento de modificar crear un nuevo obj
        System.out.println("guardar");
        product.setCode(etCodeProduct.getText().toString());
        product.setNameProduct(etNameProduct.getText().toString());
        product.setDescription(etDescriptionProduct.getText().toString());
        product.setPriceProduct(Integer.valueOf(etPriceProduct.getText().toString()));
        product.setImageProduct(R.drawable.imagen_product);
        product.setQuantity(Integer.valueOf(etQuantityProduct.getText().toString()));

        AppClientRealm.insertSingleData(product);
        listener.onClickAction(this);
    }

    private void updateproduct() {
        //TODO: falta Validar campos

        // al momento de modificar crear un nuevo obj
        System.out.println("guardar");
        Product productUpdate =  new Product();

        productUpdate.setIdProduct(product.getIdProduct());
        productUpdate.setCode(etCodeProduct.getText().toString());
        productUpdate.setNameProduct(etNameProduct.getText().toString());
        productUpdate.setDescription(etDescriptionProduct.getText().toString());
        productUpdate.setPriceProduct(Integer.valueOf(etPriceProduct.getText().toString()));
        productUpdate.setImageProduct(R.drawable.imagen_product);
        productUpdate.setQuantity(Integer.valueOf(etQuantityProduct.getText().toString()));
        productUpdate.setCategory(product.getCategory());

        AppClientRealm.insertSingleData(productUpdate);
        listener.onClickAction(this);
    }

    public void onClickDelete(View v) {

        boolean remove = AppClientRealm.removeObjectForId(Product.class, "idProduct", product.getIdProduct());
        System.out.println("remove: "+remove);
        listener.onClickAction(this);
    }

    private void setEnabledView(boolean action) {

        etCodeProduct.setEnabled(action);
        etNameProduct.setEnabled(action);
        etPriceProduct.setEnabled(action);
        etQuantityProduct.setEnabled(action);
        etDescriptionProduct.setEnabled(action);
    }

    public void setListener(DialogProductListener listener) {
        this.listener = listener;
    }

    public interface DialogProductListener {

        void onClickAction(DialogProduct dialog);

    }

}
