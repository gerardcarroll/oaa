package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/7/2016.
 */
public class Product {

    String imageBaseUrl;
    String name;
    Float formattedPrice;

    public Product(String imageBaseUrl, String name, Float formattedPrice) {
        this.imageBaseUrl = imageBaseUrl;
        this.name = name;
        this.formattedPrice = formattedPrice;
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public void setImageBaseUrl(String imageBaseUrl) {
        this.imageBaseUrl = imageBaseUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getFormattedPrice() {
        return formattedPrice;
    }

    public void setFormattedPrice(Float formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

}
