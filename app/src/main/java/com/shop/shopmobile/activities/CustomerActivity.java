package com.shop.shopmobile.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.entities.Person;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.adapters.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.adapters.SimpleViewHolder;

import java.util.List;

public class CustomerActivity extends GeneralApp implements SimpleRecyclerViewAdapter.Listener<Person> {

    @In(R.id.rv_customer)
    private RecyclerView rvCustomer;

    @In(R.id.fab_add_customer)
    private FloatingActionButton fabAddCustomer;

    private SimpleRecyclerViewAdapter<Person, MyCustomerViewHolder> adapter;
    private List<Person> listCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Clientes");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setOnClickListener(view -> { onBackPressed(); });

        fabAddCustomer.setOnClickListener(this::onClickAddCustomer);

        setCustomerView();
    }

    private void setCustomerView() {
        System.out.println("cargar customer");
    }

    public void onClickAddCustomer(View v) {
        System.out.println("add customer");
    }

    @Override
    public void simpleRecyclerViewOnCLick(Person person) {
        System.out.println("click item");
    }


    public class MyCustomerViewHolder extends SimpleViewHolder<Person> {


        public MyCustomerViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(Person data, Context context) {
            super.setData(data, context);
        }
    }
}
