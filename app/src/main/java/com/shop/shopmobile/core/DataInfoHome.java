package com.shop.shopmobile.core;

public class DataInfoHome {

    private String info;
    private String description;
    private String color;

    public DataInfoHome() { }

    public DataInfoHome(String info, String description, String color) {
        this.info = info;
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
