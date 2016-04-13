package com.overstock.android.prototype.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author LeeMeehan Created on 12-Apr-16.
 */
public class Options {

  private int optionId;

  @SerializedName("decription")
  private String description;// : "N/A",

  @SerializedName("qtyOnHand")
  private int quantityOnHand;

  private int maxQuantityAllowed;

  private float price;

  public Options(int optionId, String decription, int quantityOnHand, int maxQuantityAllowed, float price) {
    this.optionId = optionId;
    this.description = decription;
    this.quantityOnHand = quantityOnHand;
    this.maxQuantityAllowed = maxQuantityAllowed;
    this.price = price;
  }

  public int getOptionId() {
    return optionId;
  }

  public String getDescription() {
    return description;
  }

  public int getQuantityOnHand() {
    return quantityOnHand;
  }

  public int getMaxQuantityAllowed() {
    return maxQuantityAllowed;
  }

  public float getPrice() {
    return price;
  }
}
