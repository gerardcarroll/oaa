package com.overstock.android.prototype.model;

import java.util.List;

/**
 * Created by rconnolly on 3/8/2016.
 */
public class Products {

    private List<Product> products;

    public Products(){}

    public Products(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProductsList() {
        return products;
    }
}
