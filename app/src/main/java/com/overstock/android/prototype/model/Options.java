package com.overstock.android.prototype.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * @author LeeMeehan Created on 12-Apr-16.
 */
@Parcel
public class Options {

  protected int optionId;

  @SerializedName("decription")
  protected String description;

  @SerializedName("qtyOnHand")
  protected int quantityOnHand;

  protected int maxQuantityAllowed;

  protected float price;

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
