package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class GiftCardRedemptionRequest implements Parcelable {

  public static GiftCardRedemptionRequest create(GiftCardShippingAddress shippingAddress, GiftCard giftCard){
    return new AutoValue_GiftCardRedemptionRequest(shippingAddress, giftCard);
  }

  public abstract GiftCardShippingAddress shippingAddress();

  public abstract GiftCard giftCard();

}
