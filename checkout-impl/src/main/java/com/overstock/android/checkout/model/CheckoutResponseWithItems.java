package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class CheckoutResponseWithItems implements Parcelable{
  public abstract CheckoutResponse checkoutResponse();
  public abstract CheckoutItemsResponse itemsResponse();

  public static CheckoutResponseWithItems create(CheckoutResponse checkoutResponse, CheckoutItemsResponse itemsResponse) {
    return new AutoValue_CheckoutResponseWithItems(checkoutResponse, itemsResponse);
  }
}
