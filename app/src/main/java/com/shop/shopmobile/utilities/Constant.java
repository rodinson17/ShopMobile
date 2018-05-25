package com.shop.shopmobile.utilities;

import com.shop.shopmobile.core.entities.Category;
import com.shop.shopmobile.core.entities.Product;
import java.util.List;

public class Constant {

    public static final String EMPTY_STRING = "";

    public static List<Category> listCategories;
    public static List<Product> listFilterProduct;


    public static List<Category> getListCategories() { return listCategories; }

    public static void setListCategories(List<Category> listCategories) { Constant.listCategories = listCategories; }

    public static List<Product> getListFilterProduct() { return listFilterProduct; }

    public static void setListFilterProduct(List<Product> listFilterProduct) { Constant.listFilterProduct = listFilterProduct; }

}
