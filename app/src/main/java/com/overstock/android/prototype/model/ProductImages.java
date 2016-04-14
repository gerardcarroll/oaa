package com.overstock.android.prototype.model;

/**
 * Created by rconnolly on 4/13/2016.
 */
public class ProductImages {

    private String[] oViewerImages;

    private String[] imageSizes;

    private String imagePath;

    public ProductImages(){}

    public ProductImages(String[] oViewerImages, String[] imageSizes, String imagePath) {
        this.oViewerImages = oViewerImages;
        this.imageSizes = imageSizes;
        this.imagePath = imagePath;
    }

    public String[] getoViewerImages() {
        return oViewerImages;
    }

    public String[] getImageSizes() {
        return imageSizes;
    }

    public String getImagePath() {
        return imagePath;
    }
}
