package com.overstock.android.prototype.model;

/**
 * Created by rconnolly on 3/8/2016.
 */
public class ProductsResponse {

    protected Products products;

    public ProductsResponse(){}

    public ProductsResponse(Products products) {
        this.products = products;
    }

    public Products getProducts() {
        return products;
    }
}
