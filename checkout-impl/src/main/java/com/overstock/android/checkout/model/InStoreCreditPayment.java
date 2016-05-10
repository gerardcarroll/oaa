package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;
import android.support.annotation.Nullable;

@AutoValue
public abstract class InStoreCreditPayment implements Parcelable {

  public static InStoreCreditPayment create(@Nullable BasicAmount amount, boolean useAllAvailable){
    return new AutoValue_InStoreCreditPayment(amount, useAllAvailable);
  }

  @Nullable
  public abstract BasicAmount amount();

  public abstract boolean useAllAvailable();

  public Builder toBuilder(){
    return new AutoValue_InStoreCreditPayment.Builder(this);
  }

  @AutoValue.Builder
  public static abstract class Builder{

    abstract Builder amount(@Nullable BasicAmount amount);

    abstract Builder useAllAvailable(boolean available);

    public abstract InStoreCreditPayment build();

  }

}
