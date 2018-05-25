package com.shop.shopmobile.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shop.shopmobile.R;
import com.shop.shopmobile.adapters.SimpleRecyclerViewAdapter;
import com.shop.shopmobile.adapters.SimpleViewHolder;
import com.shop.shopmobile.core.entities.Product;
import com.shop.shopmobile.dialogs.DialogDetailProduct;
import com.shop.shopmobile.dialogs.DialogProduct;
import com.shop.shopmobile.interfaces.Observer;
import com.shop.shopmobile.utilities.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaceHolderFragment extends Fragment implements SimpleRecyclerViewAdapter.Listener<Product> {

    private RecyclerView recyclerView;
    private static final String ARG_CLASS = "position";
    private SimpleRecyclerViewAdapter<Product, MyViewHolder> adapter;
    private Observer observer;


    public PlaceHolderFragment() { }

    public static PlaceHolderFragment getInstance(int position) {

        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_CLASS, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_for_sale, container, false);
        recyclerView = rootView.findViewById(R.id.rv_sale);

        int pos = getArguments().getInt(ARG_CLASS);
        int idCategory = Constant.getListCategories().get(pos).getIdCategory();

        List<Product> listProduct = new ArrayList<>();

        for (Product p: Constant.getListFilterProduct()) {
            if (p.getCategory().getIdCategory() == idCategory) {
                listProduct.add(p);
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayout.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        adapter = new SimpleRecyclerViewAdapter<>(MyViewHolder.class,R.layout.recycler_sale_view_item, listProduct);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void simpleRecyclerViewOnCLick(Product product) {

        DialogDetailProduct dialogProduct = new DialogDetailProduct(getContext(), product, observer);
        dialogProduct.show();
    }

    public void setObserver(Observer observer) { this.observer = observer; }


    public class MyViewHolder extends SimpleViewHolder<Product> {

        private ImageView ivImageProduct;
        private TextView tvNameProduct;
        private TextView tvPriceProduct;
        private TextView tvQuantityOrder;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivImageProduct = itemView.findViewById(R.id.iv_image_product);
            tvNameProduct = itemView.findViewById(R.id.tv_name_product);
            tvPriceProduct = itemView.findViewById(R.id.tv_price_product);
            tvQuantityOrder = itemView.findViewById(R.id.tv_quantity_order);
        }

        @Override
        public void setData(Product data, Context context) {
            super.setData(data, context);
            Picasso.get().load(data.getImageProduct()).into(ivImageProduct);
            tvNameProduct.setText(data.getNameProduct());
            tvPriceProduct.setText("$ " + data.getPriceProduct());

//            if (data.getCantOrder() == 0) {
//                tvQuantityOrder.setVisibility(View.GONE);
//            } else {
//                tvQuantityOrder.setVisibility(View.VISIBLE);
//                tvQuantityOrder.setText("Quantity: "+data.getCantOrder());
//            }
        }
    }
}
