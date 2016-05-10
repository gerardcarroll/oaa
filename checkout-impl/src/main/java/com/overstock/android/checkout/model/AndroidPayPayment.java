package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;
import android.support.annotation.Nullable;

@AutoValue
public abstract class AndroidPayPayment implements Parcelable {

  public static Builder builder() {
    return new AutoValue_AndroidPayPayment.Builder();
  }

  @Nullable
  public abstract String paymentData();

  @Nullable
  public abstract BasicAmount amount();

  @Nullable
  public abstract String token();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder paymentData(String paymentData);
    public abstract Builder amount(BasicAmount amount);
    public abstract Builder token(String token);

    public abstract AndroidPayPayment build();
  }
}
