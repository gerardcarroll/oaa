package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import com.overstock.android.checkout.model.AmountType.AmountTypeRef;

import android.os.Parcelable;

import java.math.BigDecimal;

@AutoValue
public abstract class CheckoutAmount implements Parcelable {

  public static Builder builder(){
    return new AutoValue_CheckoutAmount.Builder();
  }

  public abstract BigDecimal numericValue();

  public abstract String currencyCode();

  public abstract String formattedValue();

  @AmountTypeRef
  public abstract String type();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder numericValue(BigDecimal value);
    public abstract Builder currencyCode(String currencyCode);
    public abstract Builder formattedValue(String formattedValue);
    public abstract Builder type(String type);
    public abstract CheckoutAmount build();
  }
}
