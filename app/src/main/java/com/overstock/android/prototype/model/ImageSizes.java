package com.overstock.android.prototype.model;

import org.parceler.Parcel;

/**
 * @author LeeMeehan
 * @since Created on 26-Apr-16.
 */
@Parcel
public class ImageSizes {

  private String imagePath;

  private int height;

  private int width;

  // Used by Gson
  public ImageSizes() {}

  public ImageSizes(final String imagePath, final int height, final int width) {
    this.imagePath = imagePath;
    this.height = height;
    this.width = width;
  }

  public String getImagePath() {
    return imagePath;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }
}
