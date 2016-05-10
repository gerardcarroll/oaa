package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;
import android.support.annotation.Nullable;

@AutoValue
public abstract class ClubOPayment implements Parcelable {

  public static ClubOPayment create(@Nullable BasicAmount amount, boolean useAllAvailable){
    return new AutoValue_ClubOPayment(amount, useAllAvailable);
  }

  @Nullable
  public abstract BasicAmount amount();

  public abstract boolean useAllAvailable();

  public Builder toBuilder(){
    return new AutoValue_ClubOPayment.Builder(this);
  }

  @AutoValue.Builder
  public static abstract class Builder {

    abstract Builder amount(@Nullable BasicAmount amount);

    abstract Builder useAllAvailable(boolean useAllAvailable);

    public abstract ClubOPayment build();

  }

}
