package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class GuestInfo implements Parcelable{
  public abstract String email();

  public static Builder builder(){
    return new AutoValue_GuestInfo.Builder();
  }

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder email(String email);
    public abstract GuestInfo build();
  }
}
