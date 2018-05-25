package com.shop.shopmobile.core.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DetailCart extends RealmObject {

    @PrimaryKey
    private int idDetailCart;
    private Cart cart;
    private Product product;
    private int quantity;
    private int price;

    public DetailCart() { }

    public DetailCart(int idDetailCart, Cart cart, Product product, int quantity, int price) {
        this.idDetailCart = idDetailCart;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public int getIdDetailCart() {
        return idDetailCart;
    }

    public void setIdDetailCart(int idDetailCart) {
        this.idDetailCart = idDetailCart;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
