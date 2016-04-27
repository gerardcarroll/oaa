package com.overstock.android.prototype.model;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.util.List;

/**
 * @author Rayconnolly Created on 3/21/2016.
 */
@Parcel
public class ProductDetail implements Parcelable {

  protected int id;

  protected String name;

  protected String imageLarge;

  protected String imageMedium1;

  protected float memberPrice;

  protected Integer quantity;

  protected String description;

  protected List<Options> options;

  protected List<ProductImages> productImages;

  protected ProductDetail() {}

  public ProductDetail(int id, String name, String imageLarge, String image, float price, int quantity,
      String description, List<Options> options, List<ProductImages> productImages) {
    this.id = id;
    this.name = name;
    this.imageLarge = imageLarge;
    this.imageMedium1 = image;
    this.memberPrice = price;
    this.quantity = quantity;
    this.description = description;
    this.options = options;
    this.productImages = productImages;
  }

  protected ProductDetail(android.os.Parcel in) {
    id = in.readInt();
    name = in.readString();
    imageLarge = in.readString();
    imageMedium1 = in.readString();
    memberPrice = in.readFloat();
    description = in.readString();
  }

  public static final Creator<ProductDetail> CREATOR = new Creator<ProductDetail>() {
    @Override
    public ProductDetail createFromParcel(android.os.Parcel in) {
      return new ProductDetail(in);
    }

    @Override
    public ProductDetail[] newArray(int size) {
      return new ProductDetail[size];
    }
  };

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImageMedium1() {
    return imageMedium1;
  }

  public float getMemberPrice() {
    return memberPrice;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getDescription() {
    return description;
  }

  public String getImageLarge() {
    return imageLarge;
  }

  public List<Options> getOptions() {
    return options;
  }

  public List<ProductImages> getProductImages() {
    return productImages;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(android.os.Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    dest.writeString(imageLarge);
    dest.writeString(imageMedium1);
    dest.writeFloat(memberPrice);
    dest.writeString(description);
  }
}
