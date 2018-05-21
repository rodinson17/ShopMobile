package com.shop.shopmobile.core.emuns;

public enum  PaymentMethod {

    METHOD_CARD("Card"),
    METHOD_CASH("Cash");

    private String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
