package com.shop.shopmobile.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.entities.Category;
import com.shop.shopmobile.core.entities.Product;
import com.shop.shopmobile.dialogs.DialogProduct;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.adapters.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.adapters.SimpleViewHolder;

import java.util.List;

public class ProductActivity extends GeneralApp implements SimpleRecyclerViewAdapter.Listener<Product>, DialogProduct.DialogProductListener {

    @In(R.id.rv_product)
    private RecyclerView rvProduct;

    @In(R.id.fab_add_product)
    private FloatingActionButton fabAddProduct;

    @In(R.id.tb_product)
    private Toolbar toolbar;

    private SimpleRecyclerViewAdapter<Product, MyProductViewHolder> adapter;
    private List<Product> listProduct;
    private int idCategory;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        idCategory = getIntent().getIntExtra("idCategory", 1);
        String nameCategory = getIntent().getStringExtra("category");
        category = AppClientRealm.getDataForID(Category.class, "idCategory", idCategory);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Productos");
        getSupportActionBar().setSubtitle(nameCategory);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> { onBackPressed(); });
        fabAddProduct.setOnClickListener(this::addProductOnClick);

        setProductInView();
    }

    public void setProductInView() {
        listProduct = AppClientRealm.getAllDataForID(Product.class, "category.idCategory", idCategory);

        rvProduct.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        rvProduct.setHasFixedSize(true);

        adapter = new SimpleRecyclerViewAdapter<>(MyProductViewHolder.class, R.layout.item_view_product, listProduct);
        adapter.setListener(this);
        rvProduct.setAdapter(adapter);
    }


    public void addProductOnClick(View v) {

        Number maxID = AppClientRealm.getMaxID(Product.class, "idProduct");
        long newID = (maxID != null) ? ((long) maxID + 1) : 1;
        int idProduct = (int) newID;
        System.out.println("id: "+idProduct);

        Product newProduct = new Product();
        newProduct.setIdProduct(idProduct);
        newProduct.setCategory(category);
        DialogProduct dialogProduct = new DialogProduct(this, newProduct);
        dialogProduct.setListener(this);
        dialogProduct.show();
    }

    @Override
    public void simpleRecyclerViewOnCLick(Product product) {
        System.out.println("product: "+product.getNameProduct());
        DialogProduct dialogProduct = new DialogProduct(this, product);
        dialogProduct.setListener(this);
        dialogProduct.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("retorno de dialog");
        //setProductInView();
    }

    @Override
    public void onClickAction(DialogProduct dialog) {
        listProduct = AppClientRealm.getAllDataForID(Product.class, "category.idCategory", idCategory);
        System.out.println("list "+listProduct.size()+" idcat:: "+idCategory);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    public class MyProductViewHolder extends SimpleViewHolder<Product> {

        private ImageView ivImageProduct;
        private TextView tvNameProduct;
        private TextView tvPriceProduct;

        public MyProductViewHolder(View itemView) {
            super(itemView);

            ivImageProduct = itemView.findViewById(R.id.iv_image_product);
            tvNameProduct = itemView.findViewById(R.id.tv_nameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tv_price_product);
        }

        @Override
        public void setData(Product data, Context context) {
            super.setData(data, context);
            tvNameProduct.setText(data.getNameProduct());
            tvPriceProduct.setText("$ "+data.getPriceProduct());
        }
    }

}
