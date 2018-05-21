package com.shop.shopmobile.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.shop.shopmobile.R;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.DataInfoHome;
import com.shop.shopmobile.core.entities.User;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.utilities.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.utilities.SimpleViewHolder;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends GeneralApp implements NavigationView.OnNavigationItemSelectedListener, SimpleRecyclerViewAdapter.Listener<DataInfoHome> {

    // region Attributes of Activity
    @In(R.id.drawer_layout)
    private DrawerLayout drawer;

    @In(R.id.nav_view)
    private NavigationView navigationView;

    @In(R.id.iv_photo_home)
    private ImageView ivPhotoHome;

    @In(R.id.tv_user_home)
    private TextView tvNameUser;

    @In(R.id.tv_role_home)
    private TextView tvRoleUser;

    @In(R.id.rv_options_home)
    private RecyclerView rvHome;

    private SimpleRecyclerViewAdapter<DataInfoHome, MyDataViewHolder> adapterHome;
    private List<DataInfoHome> listData;
    private int idUser;
    private User currentUser;

    // endregion


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tienda Movil");

        idUser = getIntent().getIntExtra("idUser", 0);
        currentUser = AppClientRealm.getDataForID(User.class, "idUser", idUser);

        Picasso.get().load(R.drawable.avatar).into(ivPhotoHome);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        insertInfoUserHeader();
        setInfoItems();
    }

    private void insertInfoUserHeader() {
        View header = navigationView.getHeaderView(0);

        CircleImageView civPhotoUser = header.findViewById(R.id.civ_photo_user);
        TextView tvNameUserHeard = header.findViewById(R.id.tv_name_user);
        TextView tvRoleUserHeard = header.findViewById(R.id.tv_role_user);

        Picasso.get().load(currentUser.getPerson().getPhoto()).into(civPhotoUser);
        tvNameUserHeard.setText(currentUser.getPerson().getFirstName() + " " + currentUser.getPerson().getLastName());
        tvRoleUserHeard.setText(currentUser.getRole());

        tvNameUser.setText("Hello, " + currentUser.getPerson().getFirstName() + " " + currentUser.getPerson().getLastName());
        tvRoleUser.setText(currentUser.getRole());
    }

    public void setInfoItems() {
        listData = new ArrayList<>();

        listData.add(new DataInfoHome("Venta", "realizar Ventas", "#ffffff"));
        listData.add(new DataInfoHome("Productos", "Info Product", "#ffffff"));
        listData.add(new DataInfoHome("Clientes", "Info Clientes", "#ffffff"));
        listData.add(new DataInfoHome("Reports", "reportes", "#ffffff"));

        rvHome.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvHome.setHasFixedSize(true);

        adapterHome = new SimpleRecyclerViewAdapter<>(MyDataViewHolder.class, R.layout.item_view_home, listData);
        adapterHome.setListener(this);
        rvHome.setAdapter(adapterHome);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intentMenu = null;

        switch (item.getItemId()) {

            case R.id.nav_home:
                System.out.println("home");
                //intentMenu = new Intent(MainActivity.this, MainActivity.class);
                break;
            case R.id.nav_products:
                intentMenu = new Intent(HomeActivity.this, CategoryActivity.class);
                break;
            case R.id.nav_employees:
                intentMenu = new Intent(HomeActivity.this, EmployeeActivity.class);
                break;
            case R.id.nav_customers:
                intentMenu = new Intent(HomeActivity.this, CustomerActivity.class);
                break;
            case R.id.nav_profile:
                intentMenu = new Intent(HomeActivity.this, ProfileActivity.class);
                intentMenu.putExtra("idUser", currentUser.getIdUser());
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        if (intentMenu != null) startActivity(intentMenu);

        return true;
    }

    private void logout() {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        AppClientRealm.realm.close();
        finish();
    }

    @Override
    public void simpleRecyclerViewOnCLick(DataInfoHome data) {

        Intent intentHome = null;

        switch (data.getInfo()) {
            case "Venta":
                intentHome = new Intent(HomeActivity.this, SaleActivity.class);
                break;
            case "Productos":
                intentHome = new Intent(HomeActivity.this, CategoryActivity.class);
                break;
            case "Clientes":
                intentHome = new Intent(HomeActivity.this, CustomerActivity.class);
                break;
            case "Reports":
                intentHome = new Intent(HomeActivity.this, ReportsActivity.class);
                break;
             default:
                 break;
        }

        if (intentHome != null) startActivity(intentHome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentUser = AppClientRealm.getDataForID(User.class, "idUser", idUser);
        insertInfoUserHeader();
    }


    public class MyDataViewHolder extends SimpleViewHolder<DataInfoHome> {

        private TextView tvInfo;
        private TextView tvDescription;
        private View view;


        public MyDataViewHolder(View itemView) {
            super(itemView);

            tvInfo = itemView.findViewById(R.id.tv_info_home);
            tvDescription = itemView.findViewById(R.id.tv_description_home);
            view = itemView.findViewById(R.id.v_color);
        }

        @Override
        public void setData(DataInfoHome data, Context context) {
            super.setData(data, context);

            tvInfo.setText(data.getInfo());
            tvDescription.setText(data.getDescription());
            //view.setBackgroundColor(Color.parseColor(data.getColor()));
        }
    }

}
