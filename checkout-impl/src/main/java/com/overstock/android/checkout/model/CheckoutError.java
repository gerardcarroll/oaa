package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

import com.overstock.android.gson.HrefWrapper;

import android.os.Parcelable;

import java.util.List;

@AutoValue
public abstract class CheckoutError implements Parcelable {

  public abstract Meta meta();

  @SerializedName("list")
  public abstract List<Error> errors();

  @AutoValue
  public static abstract class Meta implements Parcelable {
    public abstract HrefWrapper checkoutLink();
  }

  @AutoValue
  public static abstract class Error implements Parcelable {
    public abstract String messageId();
  }
}
