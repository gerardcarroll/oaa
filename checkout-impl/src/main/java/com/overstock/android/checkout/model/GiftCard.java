package com.overstock.android.checkout.model;


import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class GiftCard implements Parcelable {

  public static GiftCard create(String number, String pin){
    return new AutoValue_GiftCard(number, pin);
  }

  public abstract String number();

  public abstract String pin();

}
