package com.overstock.android.prototype.models;

import java.util.List;

/**
 * Created by rconnolly on 3/8/2016.
 */
public class ProductsResponse {

    private List<Product> products;

    public ProductsResponse(){}

    public ProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
