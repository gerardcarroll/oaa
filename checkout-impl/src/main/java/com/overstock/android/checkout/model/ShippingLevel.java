package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class ShippingLevel implements Parcelable {

  public static Builder builder(){
    return new AutoValue_ShippingLevel.Builder();
  }

  public abstract String id();

  public abstract String description();

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder id(String id);
    public abstract Builder description(String description);
    public abstract ShippingLevel build();
  }

}
