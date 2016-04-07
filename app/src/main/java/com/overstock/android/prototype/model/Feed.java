package com.overstock.android.prototype.model;

/**
 * Created by rconnolly on 3/16/2016.
 */
public class Feed {

    private int productImage;
    private String topProductsLink;
    private String productUrl;

    public Feed() {}

    public Feed(int productImage, String topProductsLink, String productUrl) {
        this.productImage = productImage;
        this.topProductsLink = topProductsLink;
        this.productUrl = productUrl;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getTopProductsLink() {
        return topProductsLink;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public void setTopProductsLink(String topProductsLink) {
        this.topProductsLink = topProductsLink;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
