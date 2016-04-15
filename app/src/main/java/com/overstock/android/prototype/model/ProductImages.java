package com.overstock.android.prototype.model;

import org.parceler.Parcel;

/**
 * Created by rconnolly on 4/13/2016.
 */
@Parcel
public class ProductImages {

    private String imagePath;

    public ProductImages(){}

    public ProductImages(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
