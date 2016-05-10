package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;
import android.support.annotation.Nullable;

@AutoValue
public abstract class CheckoutPayment implements Parcelable {

  public static Builder builder(){
    return new AutoValue_CheckoutPayment.Builder()
      .giftCardPayment(GiftCardPayment.create(null, true))
      .instoreCreditPayment(InStoreCreditPayment.create(null, true));
  }

  //TODO creditCardPayment, clubOPayment, klarnaPayment, payPalPayment,
  // payPalCreditPayment, bitcoinPayment

  @Nullable
  public abstract AndroidPayPayment androidPayPayment();

  @Nullable
  public abstract GiftCardPayment giftCardPayment();

  @Nullable
  public abstract InStoreCreditPayment instoreCreditPayment();

  @Nullable
  public abstract ClubOPayment clubOPayment();

  public Builder toBuilder(){
    return new AutoValue_CheckoutPayment.Builder(this);
  }

  @AutoValue.Builder
  public static abstract class Builder{
    abstract Builder androidPayPayment(AndroidPayPayment androidPayPayment);
    abstract Builder giftCardPayment(GiftCardPayment giftCardPayment);
    abstract Builder instoreCreditPayment(InStoreCreditPayment inStoreCreditPayment);
    abstract Builder clubOPayment(ClubOPayment clubOPayment);
    public abstract CheckoutPayment build();
  }
}
