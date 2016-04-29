package com.overstock.android.prototype.model;

import org.parceler.Parcel;

import android.os.Parcelable;

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

  protected List<OViewerImage> oViewerImages;

  protected ProductDetail() {}

  public ProductDetail(final int id, final String name, final String imageLarge, final String image, final float price,
      final int quantity, final String description, final List<Options> options,
      final List<OViewerImage> oViewerImages) {
    this.id = id;
    this.name = name;
    this.imageLarge = imageLarge;
    this.imageMedium1 = image;
    this.memberPrice = price;
    this.quantity = quantity;
    this.description = description;
    this.options = options;
    this.oViewerImages = oViewerImages;
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

  public List<OViewerImage> getoViewerImages() {
    return oViewerImages;
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
