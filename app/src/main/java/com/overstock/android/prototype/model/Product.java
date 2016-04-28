package com.overstock.android.prototype.model;

import android.os.Parcelable;

import org.parceler.Parcel;

/**
 * @author RayConnolly on 3/7/2016.
 */
@Parcel
public class Product implements Parcelable {

  protected int id;

  protected String imageLarge;

  protected String imageMedium1;

  protected String name;

  protected float memberPrice;

  protected String imageThumbnail;

  public Product() {}

  public Product(final int id, final String imageLarge, final String imageMedium1, final String imageThumbnail,
      final String name, final float memberPrice) {
    this.id = id;
    this.imageLarge = imageLarge;
    this.imageMedium1 = imageMedium1;
    this.imageThumbnail = imageThumbnail;
    this.name = name;
    this.memberPrice = memberPrice;
  }

  protected Product(android.os.Parcel in) {
    id = in.readInt();
    imageLarge = in.readString();
    imageMedium1 = in.readString();
    name = in.readString();
    memberPrice = in.readFloat();
  }

  public static final Creator<Product> CREATOR = new Creator<Product>() {
    @Override
    public Product createFromParcel(android.os.Parcel in) {
      return new Product(in);
    }

    @Override
    public Product[] newArray(int size) {
      return new Product[size];
    }
  };

  public int getId() {
    return id;
  }

  public String getImageMedium1() {
    return imageMedium1;
  }

  public String getName() {
    return name;
  }

  public float getMemberPrice() {
    return memberPrice;
  }

  public String getImageLarge() {
    return imageLarge;
  }

  public String getImageThumbnail() {
    return imageThumbnail;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(imageLarge);
    dest.writeString(imageMedium1);
    dest.writeString(name);
    dest.writeFloat(memberPrice);
  }
}
