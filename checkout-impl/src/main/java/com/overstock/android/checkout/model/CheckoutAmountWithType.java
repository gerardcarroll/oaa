package com.overstock.android.checkout.model;

import android.os.Parcel;
import android.os.Parcelable;

//Can't use Auto Value because we want to have our own TypeAdapter
public class CheckoutAmountWithType implements Parcelable {

  public static final Creator<CheckoutAmountWithType> CREATOR = new Creator<CheckoutAmountWithType>() {
    @Override
    public CheckoutAmountWithType createFromParcel(Parcel in) {
      return new CheckoutAmountWithType(in);
    }

    @Override
    public CheckoutAmountWithType[] newArray(int size) {
      return new CheckoutAmountWithType[size];
    }
  };
  private final String type;
  private final CheckoutAmount amount;

  public CheckoutAmountWithType(String type, CheckoutAmount amount) {
    this.type = type;
    this.amount = amount;
  }

  protected CheckoutAmountWithType(Parcel in) {
    type = in.readString();
    amount = in.readParcelable(CheckoutAmount.class.getClassLoader());
  }

  public String type() {
    return type;
  }

  public CheckoutAmount amount() {
    return amount;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(type);
    dest.writeParcelable(amount, flags);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CheckoutAmountWithType that = (CheckoutAmountWithType) o;

    if (!type.equals(that.type)) {
      return false;
    }
    return amount != null ? amount.equals(that.amount) : that.amount == null;
  }

  @Override
  public int hashCode() {
    int result = type.hashCode();
    result = 31 * result + (amount != null ? amount.hashCode() : 0);
    return result;
  }
}
