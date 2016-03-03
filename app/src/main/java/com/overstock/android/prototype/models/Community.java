package com.overstock.android.prototype.models;

import org.parceler.Parcel;

/**
 * Created by rconnolly on 3/1/2016.
 */
@Parcel
public class Community {

  int imageId;

  String name;

  boolean isSelected;

  public Community() { /* Required empty constructor */ }

  public Community(final int imageId, final String name) {
    this.imageId = imageId;
    this.name = name;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(final int imageId) {
    this.imageId = imageId;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(final boolean isSelected) {
    this.isSelected = isSelected;
  }
}
