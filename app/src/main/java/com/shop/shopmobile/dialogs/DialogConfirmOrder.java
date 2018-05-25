package com.shop.shopmobile.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.shop.shopmobile.R;
import com.shop.shopmobile.adapters.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.adapters.SimpleViewHolder;
import com.shop.shopmobile.core.entities.Product;
import com.shop.shopmobile.interfaces.EventOnClickSave;
import com.shop.shopmobile.utilities.Constant;

import java.util.ArrayList;
import java.util.List;

public class DialogConfirmOrder extends Dialog implements SimpleRecyclerViewAdapter.Listener<Product>, EventOnClickSave {

    // region Attributes of class
    private TextView tvCancel;
    private TextView tvConfirm;
    private TextView tvNumberTable;
    private TextView tvTotalOrder;
    private Spinner sTables;
    private RecyclerView rvDetailsOrder;

    private SimpleRecyclerViewAdapter<Product, MyOrderViewHolder> adapter;
    private DialogConfirmListener listener;
    private List<Product> listProducts;
    private List<Product> newListProduct;
    private String listTable;
    private ArrayAdapter<String> adapterSpinner;
    // endregion

    public DialogConfirmOrder(@NonNull Context context, List<Product> list) {
        super(context);
        this.listProducts = list;
        init();
    }

    private DialogConfirmOrder(@NonNull Context context, int themeResId) {  super(context, themeResId);  }


    public void init() {
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setContentView(R.layout.dialog_confirm_order);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        tvCancel = findViewById(R.id.tv_cancel_dco);
        tvConfirm = findViewById(R.id.tv_confirm_dco);
        tvNumberTable = findViewById(R.id.tv_number_table_d);
        tvTotalOrder = findViewById(R.id.tv_total_order);
        sTables = findViewById(R.id.s_tables);
        rvDetailsOrder = findViewById(R.id.rv_details_order);

//        setInfoInView();
//
//        tvCancel.setOnClickListener(view -> { dismiss(); });
//        tvConfirm.setOnClickListener(this::onClickConfirm);
    }

//    private void setInfoInView() {
//
//        if (tableSelect == null) {
//            sTables.setVisibility(View.VISIBLE);
//            functionForSpinner();
//        } else {
//            tvNumberTable.setVisibility(View.VISIBLE);
//            tvNumberTable.setText("Table "+tableSelect.getNumber());
//        }
//
//        int priceTotal = 0;
//        newListProduct = new ArrayList<>();
//
//        for (Product p: listProducts) { // es necesario copiar la lista para evitar cambios en la original
//            Product newProduct = new Product(p.getCode(), p.getName(), p.getDescription(), p.getPrice(), p.getImage(), p.getTag());
//            newProduct.setQuantity(p.getQuantity());
//            newListProduct.add(newProduct);
//        }
//
//        for (Product p: newListProduct) {
//            priceTotal += p.getPrice() * p.getQuantity();
//        }
//
//        tvTotalOrder.setText("$ "+priceTotal);
//        rvDetailsOrder.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
//        rvDetailsOrder.setHasFixedSize(true);
//
//        adapter = new SimpleRecyclerViewAdapter<>(MyOrderViewHolder.class, R.layout.item_view_details_order, newListProduct);
//        adapter.setListener(this);
//        adapter.setOnClickSave(this);
//        rvDetailsOrder.setAdapter(adapter);
//    }
//
//    private void functionForSpinner() {
//
//        List<Table> listTables = new Gson().fromJson(listTable, new TypeToken<List<Table>>(){}.getType());
//        List<Table> listTableAvailable = new ArrayList<>();
//        List<String> list = new ArrayList<>();
//        list.add("Select Table");
//        String table = "Table ";
//
//        for (Table t: listTables){
//            if (t.isAvailable()) {
//                listTableAvailable.add(t);
//                list.add(table + t.getNumber());
//            }
//        }
//
//        adapterSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
//        sTables.setAdapter(adapterSpinner);
//
//       sTables.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//           @Override
//           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//               if (i > 0) tableSelect = listTableAvailable.get(i - 1);
//           }
//
//           @Override
//           public void onNothingSelected(AdapterView<?> adapterView) { }
//       });
//    }
//
//
//    // region Listener
//    private void onClickConfirm(View v) {
//
//        if (tableSelect == null) {
//            Toast.makeText(getContext(), "Select an table", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        int cont = 0;
//        for (Product p: newListProduct) {
//            if (p.getQuantity() == 0) cont++;
//        }
//
//        if (cont == newListProduct.size()) {
//            Toast.makeText(getContext(), "Select an Product", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        listener.onClickConfirmOrder(newListProduct, tableSelect);
//        dismiss();
//    }

    @Override
    public void simpleRecyclerViewOnCLick(Product s) {
        // do nothing
    }

    public void setListener(DialogConfirmListener listener) { this.listener = listener; }

    @Override
    public void onClickSave() {
//        int priceTotal = 0;
//
//        for (Product p: newListProduct) {
//            priceTotal += p.getPrice() * p.getQuantity();
//        }
//        tvTotalOrder.setText("$ "+priceTotal);
    }
    // endregion

    public interface DialogConfirmListener {

        void onClickConfirmOrder(List<Product> listProductOrder);

    }


    public class MyOrderViewHolder extends SimpleViewHolder<Product> {

        private TextView tvName;
        private TextView tvQuantity;
        private TextView tvPrice;
        private ImageView ivEdit;
        private ImageView ivRemove;
        private ImageView ivAdd;
        private Product product;
        private boolean edit;
        private EventOnClickSave onClickSave;


        public MyOrderViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_product_order);
            tvQuantity = itemView.findViewById(R.id.tv_quantity_product_order);
            tvPrice = itemView.findViewById(R.id.tv_price_product_order);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivRemove = itemView.findViewById(R.id.iv_remove_item);
            ivAdd = itemView.findViewById(R.id.iv_add_item);

            ivEdit.setOnClickListener(this::editOnClick);
            ivRemove.setOnClickListener(this::removeOnClick);
            ivAdd.setOnClickListener(this::addOnClick);
        }

        @Override
        public void setData(Product data, Context context) {
            super.setData(data, context);
            this.product = data;

//            tvName.setText(data.getName());
//            tvQuantity.setText(data.getQuantity() + Constant.EMPTY_STRING);
//            tvPrice.setText(data.getPrice() + Constant.EMPTY_STRING);
        }

//        @Override
//        public void event(EventOnClickSave onClickSave) {
//            super.event(onClickSave);
//            this.onClickSave = onClickSave;
//        }

        // region Listener ViewHolder
        public void editOnClick(View v) {

            if (edit) {
                ivRemove.setVisibility(View.GONE);
                ivAdd.setVisibility(View.GONE);
                ivEdit.setImageResource(R.mipmap.ic_edit);
                edit = false;
                onClickSave.onClickSave();
            } else {
                ivRemove.setVisibility(View.VISIBLE);
                ivAdd.setVisibility(View.VISIBLE);
                //ivEdit.setImageResource(R.mipmap.ic_save);
                edit = true;
            }
        }

        public void removeOnClick(View v) {

            int cant = product.getQuantity();
            cant--;

            if (cant < 0) {
                tvQuantity.setText("0");
                product.setQuantity(0);
            } else {
                tvQuantity.setText(cant + Constant.EMPTY_STRING);
                product.setQuantity(cant);
            }
        }

        public void addOnClick(View v) {

            int cant = product.getQuantity();
            cant++;
            tvQuantity.setText(cant + Constant.EMPTY_STRING);
            product.setQuantity(cant);
        }
        // endregion
    }

}
