package com.overstock.android.prototype.model;

import org.parceler.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 * @author LeeMeehan Created on 12-Apr-16.
 */
@Parcel
public class Options {

  private int optionId;

  @SerializedName("decription")
  private String description;

  @SerializedName("qtyOnHand")
  private int quantityOnHand;

  private int maxQuantityAllowed;

  private float price;

  public Options() {

  }

  public Options(int optionId, String description, int quantityOnHand, int maxQuantityAllowed, float price) {
    this.optionId = optionId;
    this.description = description;
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
