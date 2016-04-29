package com.overstock.android.prototype.model;

import java.util.List;

import org.parceler.Parcel;

/**
 * @author Rayconnolly Created on 3/21/2016.
 */
@Parcel
public class ProductDetail {

  protected int id;

  protected String name;

  protected String imageLarge;

  protected String imageMedium1;

  protected float memberPrice;

  protected Integer quantity;

  protected String description;

  protected List<Options> options;

  protected List<OViewerImage> oViewerImages;

  protected String reviews;

  protected ProductDetail() {}

  public ProductDetail(final int id, final String name, final String imageLarge, final String image, final float price,
      final int quantity, final String description, final List<Options> options, final List<OViewerImage> oViewerImages,
      String reviews) {
    this.id = id;
    this.name = name;
    this.imageLarge = imageLarge;
    this.imageMedium1 = image;
    this.memberPrice = price;
    this.quantity = quantity;
    this.description = description;
    this.options = options;
    this.oViewerImages = oViewerImages;
    this.reviews = reviews;
  }

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

  public String getReviews() {
    return reviews;
  }
}
