package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/7/2016.
 */
public class Product {

    String image;
    String name;
    Float price;

    public Product(String image, String name, Float price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
