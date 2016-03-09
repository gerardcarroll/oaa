package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/8/2016.
 */
public class ProductsResponse {

    private Products products;

    public ProductsResponse(){}

    public ProductsResponse(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }
}
