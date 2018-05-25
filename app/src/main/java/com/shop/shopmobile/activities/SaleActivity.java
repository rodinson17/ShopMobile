package com.shop.shopmobile.activities;

import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.shop.shopmobile.R;
import com.shop.shopmobile.adapters.MyAdapterViewPager;
import com.shop.shopmobile.annotation.In;
import com.shop.shopmobile.core.AppClientRealm;
import com.shop.shopmobile.core.entities.Cart;
import com.shop.shopmobile.core.entities.Category;
import com.shop.shopmobile.core.entities.DetailCart;
import com.shop.shopmobile.core.entities.Product;
import com.shop.shopmobile.dialogs.DialogConfirmOrder;
import com.shop.shopmobile.interfaces.Observer;
import com.shop.shopmobile.utilities.Constant;
import com.shop.shopmobile.utilities.GeneralApp;
import com.shop.shopmobile.utilities.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleActivity extends GeneralApp implements DialogConfirmOrder.DialogConfirmListener, Observer {

    @In(R.id.tb_sale)
    private Toolbar toolbar;

    @In(R.id.vp_sale)
    private ViewPager viewPager;

    @In(R.id.tl_sale)
    private TabLayout tabLayout;

    private LayerDrawable icon;
    private MyAdapterViewPager adapterViewPager;
    private List<Category> listCategories;
    private List<Product> listProducts;
    private Cart cart;
    private Map<Product, Integer> mapDetailCarts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ventas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> { onBackPressed(); });

        listCategories = AppClientRealm.getAllData(Category.class);
        Constant.setListCategories(listCategories);
        getListAllProduct();
        // crear el carrito de compras
        cart = new Cart();
        mapDetailCarts = new HashMap<>();

        adapterViewPager = new MyAdapterViewPager(getSupportFragmentManager(), listCategories, this);
        viewPager.setAdapter(adapterViewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sale, menu);
        MenuItem item = menu.findItem(R.id.action_shop);

        // obtener drawable del item
        icon = (LayerDrawable) item.getIcon();

        // actualizar el contador
        Utils.setBadgeCount(this, icon, mapDetailCarts.size());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_shop) {

            if (mapDetailCarts.isEmpty()) {
                Toast.makeText(this, "Seleccione almenos un producto", Toast.LENGTH_SHORT).show();
                return true;
            }

//            DialogConfirmOrder dialogConfirmOrder = new DialogConfirmOrder(this, listProductsOrder);
//            dialogConfirmOrder.setListener(this);
//            dialogConfirmOrder.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getListAllProduct() {

        listProducts = AppClientRealm.getAllData(Product.class);
        System.out.println("can list prod: "+listProducts.size());

        // TODO: crea nuevos productos con observer

        Constant.setListFilterProduct(listProducts);

    }

    @Override
    public void onClickConfirmOrder(List<Product> listProductOrder) {
        // TODO: guardar los datos en la base local
        // apartir de los datos del mapa se crea los detalles del cart se completa

    }

    @Override
    public void addProductDetailCart(Product product, int cant) {

        System.out.println("size map: "+mapDetailCarts.size());
        if (mapDetailCarts.containsKey(product)) mapDetailCarts.remove(product);
        System.out.println("size map: "+mapDetailCarts.size());
        if (cant > 0) mapDetailCarts.put(product, cant);
        System.out.println("size map: "+mapDetailCarts.size());
        Utils.setBadgeCount(this, icon, mapDetailCarts.size());
//        for (Map.Entry<Product, Integer> entry: mapDetailCarts.entrySet()) {
//            System.out.println("name: "+entry.getKey().getNameProduct() + "  cant: "+entry.getValue());
//        }
    }



}
