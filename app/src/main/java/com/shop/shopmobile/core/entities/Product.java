package com.shop.shopmobile.core.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {

    @PrimaryKey
    private int IdProduct;
    private String code;
    private String nameProduct;
    private String description;
    private int priceProduct;
    private int imageProduct;
    private int quantity;
    private boolean available;
    private Category category;


    public Product() { }

    public Product(int idProduct, String code, String nameProduct, String description, int priceProduct, int imageProduct, int quantity, boolean available, Category category) {
        IdProduct = idProduct;
        this.code = code;
        this.nameProduct = nameProduct;
        this.description = description;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.quantity = quantity;
        this.available = available;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "IdProduct=" + IdProduct +
                ", code='" + code + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", description='" + description + '\'' +
                ", priceProduct=" + priceProduct +
                ", imageProduct=" + imageProduct +
                ", quantity=" + quantity +
                ", available=" + available +
                ", category=" + category +
                '}';
    }

    public int getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(int idProduct) {
        IdProduct = idProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
