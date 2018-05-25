package com.shop.shopmobile.core.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cart extends RealmObject {

    @PrimaryKey
    private int idCart;
    private String date;
    private Person customer;
    private User seller;
    private String paymentMethod;


    public Cart() { }

    public Cart(int idCart, String date, Person customer, User seller, String paymentMethod) {
        this.idCart = idCart;
        this.date = date;
        this.customer = customer;
        this.seller = seller;
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

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
