package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@AutoValue
public abstract class BasicAmount implements Parcelable {

  public static BasicAmount createBasicUSDAmount(BigDecimal value){
    NumberFormat df = DecimalFormat.getCurrencyInstance(Locale.US);
    return new AutoValue_BasicAmount(value, "USD", df.format(value));
  }

  public abstract BigDecimal numericValue();

  public abstract String currencyCode();

  public abstract String formattedValue();
}
