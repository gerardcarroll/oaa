package com.overstock.android.prototype.model;

import java.util.List;

/**
 * @author Rayconnolly Created on 3/21/2016.
 */
public class ProductDetail {

  private int id;

  private String name;

  private String imageLarge;

  private String imageMedium1;

  private String memberPrice;

  private Integer quantity;

  private String description;

  private List<ProductImages> productImages;

  public ProductDetail(int id, String name, String imageLarge, String image, String price, int quantity,
      String description, List<ProductImages> productImages) {
    this.id = id;
    this.name = name;
    this.imageLarge = imageLarge;
    this.imageMedium1 = image;
    this.memberPrice = price;
    this.quantity = quantity;
    this.description = description;
    this.productImages = productImages;
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

  public String getMemberPrice() {
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

  public List<ProductImages> getProductImages() { return productImages; }
}
