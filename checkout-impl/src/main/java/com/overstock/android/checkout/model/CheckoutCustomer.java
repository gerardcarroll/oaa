package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class CheckoutCustomer implements Parcelable{

  public static Builder builder(){
    return new AutoValue_CheckoutCustomer.Builder();
  }

  public abstract long id();

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder id(long id);
    public abstract CheckoutCustomer build();
  }
}
