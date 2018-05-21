package com.shop.shopmobile.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.entities.Person;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.utilities.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.utilities.SimpleViewHolder;

import java.util.List;

public class EmployeeActivity extends GeneralApp implements SimpleRecyclerViewAdapter.Listener<Person> {

    @In(R.id.rv_employees)
    private RecyclerView rvEmployee;

    @In(R.id.fab_add_employee)
    private FloatingActionButton fabAddEmployee;

    private SimpleRecyclerViewAdapter<Person, MyEmployeeViewHolder> adapter;
    private List<Person> listEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Employees");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setOnClickListener(view -> { onBackPressed(); });

        fabAddEmployee.setOnClickListener(this::OnClickAddEmployee);

        setEmployeesView();
    }

    private void setEmployeesView() {
        System.out.println("Cargar employees");
    }

    public void OnClickAddEmployee(View v) {
        System.out.println("agregar Person");
    }


    @Override
    public void simpleRecyclerViewOnCLick(Person person) {
        System.out.println("click item");
    }


    public class MyEmployeeViewHolder extends SimpleViewHolder<Person> {


        public MyEmployeeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(Person data, Context context) {
            super.setData(data, context);
        }
    }
}
