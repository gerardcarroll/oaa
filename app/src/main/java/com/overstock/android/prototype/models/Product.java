package com.overstock.android.prototype.models;

/**
 * Created by rconnolly on 3/7/2016.
 */
public class Product {

  String imageLarge;

  String name;

  Float memberPrice;

  public Product() {}

  public Product(String imageLarge, String name, Float memberPrice) {
    this.imageLarge = imageLarge;
    this.name = name;
    this.memberPrice = memberPrice;
  }

  public String getImageLarge() {
    return imageLarge;
  }

  public String getName() {
    return name;
  }

  public Float getMemberPrice() {
    return memberPrice;
  }

}
