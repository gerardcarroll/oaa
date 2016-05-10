package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class GiftCardRedemptionError implements Parcelable {

  public static GiftCardRedemptionError create(String code, String message){
    return new AutoValue_GiftCardRedemptionError(code, message);
  }

  public abstract String code();

  public abstract String message();

}
