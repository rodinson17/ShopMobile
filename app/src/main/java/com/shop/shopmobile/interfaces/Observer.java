package com.shop.shopmobile.interfaces;

import com.shop.shopmobile.core.entities.Product;

public interface Observer {

    void addProductDetailCart(Product product, int cant);

}
