package com.overstock.android.prototype.model;

import org.parceler.Parcel;

/**
 * @author RayConnolly on 3/7/2016.
 */
@Parcel
public class Product {

  private Integer id;

  private String imageLarge;

  private String imageMedium1;

  private String name;

  private Float memberPrice;

  public Product() {}

  public Product(final int id, final String imageLarge, final String imageMedium, final String name,
      final Float memberPrice) {
    this.id = id;
    this.imageLarge = imageLarge;
    this.imageMedium1 = imageMedium;
    this.name = name;
    this.memberPrice = memberPrice;
  }

  public int getId() {
    return id;
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

  public String getImageLarge() {
    return imageLarge;
  }
}
