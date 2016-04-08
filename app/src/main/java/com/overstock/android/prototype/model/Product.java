package com.overstock.android.prototype.model;

import android.os.Parcelable;

import org.parceler.Parcel;

/**
 * @author RayConnolly on 3/7/2016.
 */
@Parcel
public class Product implements Parcelable{

  private int id;

  private String imageLarge;

  private String imageMedium1;

  private String name;

  private float memberPrice;

  public Product() {}

  public Product(final int id, final String imageLarge, final String imageMedium, final String name,
      final float memberPrice) {
    this.id = id;
    this.imageLarge = imageLarge;
    this.imageMedium1 = imageMedium;
    this.name = name;
    this.memberPrice = memberPrice;
  }

  protected Product(android.os.Parcel in) {
    imageLarge = in.readString();
    imageMedium1 = in.readString();
    name = in.readString();
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeString(imageLarge);
    dest.writeString(imageMedium1);
    dest.writeString(name);
  }
}
