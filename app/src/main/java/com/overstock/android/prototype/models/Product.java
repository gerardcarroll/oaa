package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/7/2016.
 */
public class Product {

  String imageBaseUrl;

  String name;

  Float formattedPrice;

  public Product() {}

  public Product(String imageBaseUrl, String name, Float formattedPrice) {
    this.imageBaseUrl = imageBaseUrl;
    this.name = name;
    this.formattedPrice = formattedPrice;
  }

  public String getImageBaseUrl() {
    return imageBaseUrl;
  }

  public String getName() {
    return name;
  }

  public Float getFormattedPrice() {
    return formattedPrice;
  }

}
