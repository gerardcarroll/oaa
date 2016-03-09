package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/7/2016.
 */
public class Product {

  String imageMedium1;

  String name;

  Float memberPrice;

  public Product() {}

  public Product(String imageMedium, String name, Float memberPrice) {
    this.imageMedium1 = imageMedium;
    this.name = name;
    this.memberPrice = memberPrice;
  }

  public String getImageMedium1() {
    return imageMedium1;
  }

  public String getName() {
    return name;
  }

  public Float getMemberPrice() {
    return memberPrice;
  }

}
