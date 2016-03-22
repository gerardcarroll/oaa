package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/21/2016.
 */
public class ProductDetail {

    private String name;
    private String imageMedium1;
    private String memberPrice;
    private String[] options;
    private int quantity;
    private String description;

    public ProductDetail(String name, String image, String price, String[] options, int quantity, String description) {
        this.name = name;
        this.imageMedium1 = image;
        this.memberPrice = price;
        this.options = options;
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageMedium1() {
        return imageMedium1;
    }

    public void setImageMedium1(String imageMedium1) {
        this.imageMedium1 = imageMedium1;
    }

    public String getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(String memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
