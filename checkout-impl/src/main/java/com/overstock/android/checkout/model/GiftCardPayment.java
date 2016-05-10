package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;
import android.support.annotation.Nullable;

@AutoValue
public abstract class GiftCardPayment implements Parcelable {

  public static GiftCardPayment create(@Nullable BasicAmount amount, boolean useAllAvailable){
    return new AutoValue_GiftCardPayment(amount, useAllAvailable);
  }

  @Nullable
  public abstract BasicAmount amount();

  public abstract boolean useAllAvailable();

  public Builder toBuilder() {
    return new AutoValue_GiftCardPayment.Builder(this);
  }

  @AutoValue.Builder
  public static abstract class Builder{

    abstract Builder amount(@Nullable BasicAmount amount);

    abstract Builder useAllAvailable(boolean available);

    public abstract GiftCardPayment build();

  }

}
