package com.shop.shopmobile.core.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject {


    /*public class Person extends RealmObject {
    @PrimaryKey
    private String id = UUID.randomUUID().toString();
    private String name;
}

realm.beginTransaction();
Number maxValue = realm.where(MyObject.class).max("primaryKeyField");
long pk = (maxValue != null) ? maxValue + 1 : 0;
realm.createObject(MyObject.class, pk++);
realm.createObject(MyObject.class, pk++);
realm.commitTransaction();

*/


    @PrimaryKey
    private int idCategory;
    private String nameCategory;


    public Category() { }

    public Category(int idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", nameCategory='" + nameCategory + '\'' +
                '}';
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

}
