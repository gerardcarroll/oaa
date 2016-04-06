package com.overstock.android.prototype.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rconnolly on 3/1/2016.
 */
public class Community implements Parcelable{

  private int imageId;
  private String name;
  private boolean selected;

  public Community() { /* Required empty constructor */ }

  public Community(final int imageId, final String name) {
    this.imageId = imageId;
    this.name = name;
  }

  protected Community(Parcel in) {
    imageId = in.readInt();
    name = in.readString();
    selected = in.readByte() != 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(imageId);
    dest.writeString(name);
    dest.writeByte((byte) (selected ? 1 : 0));
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<Community> CREATOR = new Creator<Community>() {
    @Override
    public Community createFromParcel(Parcel in) {
      return new Community(in);
    }

    @Override
    public Community[] newArray(int size) {
      return new Community[size];
    }
  };

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
    return selected;
  }

  public void setSelected(final boolean isSelected) {
    this.selected = isSelected;
  }
}
