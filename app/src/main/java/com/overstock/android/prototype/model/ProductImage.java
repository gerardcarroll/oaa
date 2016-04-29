package com.overstock.android.prototype.model;

import org.parceler.Parcel;

/**
 * @author rconnolly
 * @since 4/13/2016.
 */
@Parcel
public class ProductImage {

  protected String imagePath;

  public ProductImage() {}

  public ProductImage(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getImagePath() {
    return imagePath;
  }
}
