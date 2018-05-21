package com.shop.shopmobile.core.entities;

import org.joda.time.DateTime;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cart extends RealmObject {

    @PrimaryKey
    private int idCart;
    private String date;
    private int idCustomer;
    private String paymentMethod;


    public Cart() { }

    public Cart(int idCart, String date, int idCustomer, String paymentMethod) {
        this.idCart = idCart;
        this.date = date;
        this.idCustomer = idCustomer;
        this.paymentMethod = paymentMethod;
    }


    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
