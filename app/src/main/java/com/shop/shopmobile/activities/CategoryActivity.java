package com.shop.shopmobile.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.entities.Category;
import com.shop.shopmobile.utilities.Constant;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.utilities.MyTextWatcher;
import com.shop.shopmobile.utilities.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.utilities.SimpleViewHolder;
import java.util.List;

public class CategoryActivity extends GeneralApp implements SimpleRecyclerViewAdapter.Listener<Category> {

    // region Attributes of Activity
    @In(R.id.rv_category)
    private RecyclerView rvCategory;

    @In(R.id.fab_add_category)
    private FloatingActionButton fabAddCategory;

    private SimpleRecyclerViewAdapter<Category, MyCategoryViewHolder> adapter;
    private List<Category> listCategory;
    // endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Categorias");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(view -> { onBackPressed(); });

        fabAddCategory.setOnClickListener(this::addCategoryOnClick);

        setCategoriesInView();
    }

    private void setCategoriesInView() {

        listCategory = AppClientRealm.getAllData(Category.class);
        System.out.println("listCategory: "+listCategory.size());

        rvCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        rvCategory.setHasFixedSize(true);

        adapter = new SimpleRecyclerViewAdapter<>(MyCategoryViewHolder.class, R.layout.item_view_category, listCategory);
        adapter.setListener(this);
        rvCategory.setAdapter(adapter);
    }

    private void addCategoryOnClick(View view) {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_category);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextInputLayout tilNameCategory = dialog.findViewById(R.id.til_name_category);
        EditText etNameCategory = dialog.findViewById(R.id.et_name_category);
        Button btnCancel = dialog.findViewById(R.id.btnCancelGenericDialog);
        Button btnSave = dialog.findViewById(R.id.btnOkGenericDialog);

        etNameCategory.addTextChangedListener(new MyTextWatcher(tilNameCategory));

        btnCancel.setOnClickListener(v -> { dialog.dismiss(); });

        btnSave.setOnClickListener(view1 -> { addNewCategory(tilNameCategory, etNameCategory, dialog); });

        dialog.show();
    }

    private void addNewCategory(TextInputLayout tilNameCategory, EditText etNameCategory, Dialog dialog) {

        if (etNameCategory.getText().toString().equals(Constant.EMPTY_STRING)) {
            tilNameCategory.setError("Insert Name Category");
            return;
        }

        Category newCategory = null;

        if (listCategory.isEmpty()) {

            newCategory = new Category(1, etNameCategory.getText().toString());
            AppClientRealm.insertSingleData(newCategory);
        } else {

            Number maxID = AppClientRealm.getMaxID(Category.class, "idCategory");
            long newID = (maxID != null) ? ((long) maxID + 1) : 0;

            newCategory = new Category((int) newID, etNameCategory.getText().toString());
            AppClientRealm.insertSingleData(newCategory);
        }

        listCategory = AppClientRealm.getAllData(Category.class);
        adapter.notifyDataSetChanged();
        dialog.dismiss();
    }

    @Override
    public void simpleRecyclerViewOnCLick(Category category) {

        Intent intentProduct = new Intent(CategoryActivity.this, ProductActivity.class);
        intentProduct.putExtra("idCategory", category.getIdCategory());
        intentProduct.putExtra("category", category.getNameCategory());
        startActivity(intentProduct);
    }


    public class MyCategoryViewHolder extends SimpleViewHolder<Category> {

        private TextView tvNameCategory;

        public MyCategoryViewHolder(View itemView) {
            super(itemView);

            tvNameCategory = itemView.findViewById(R.id.tv_name_category);
        }

        @Override
        public void setData(Category data, Context context) {
            super.setData(data, context);
            tvNameCategory.setText(data.getNameCategory());
        }
    }

}
