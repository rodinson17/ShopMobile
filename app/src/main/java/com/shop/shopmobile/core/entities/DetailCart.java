package com.shop.shopmobile.core.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DetailCart extends RealmObject {

    @PrimaryKey
    private int idDetailCart;
    private int idCart;
    private int idProduct;
    private int quantity;
    private int price;

    public DetailCart() { }

    public DetailCart(int idDetailCart, int idCart, int idProduct, int quantity, int price) {
        this.idDetailCart = idDetailCart;
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdDetailCart() {
        return idDetailCart;
    }

    public void setIdDetailCart(int idDetailCart) {
        this.idDetailCart = idDetailCart;
    }

    public int getidCart() {
        return idCart;
    }

    public void setidCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
