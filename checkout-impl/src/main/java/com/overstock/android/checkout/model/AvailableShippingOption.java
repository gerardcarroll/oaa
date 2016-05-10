package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.List;

@AutoValue
public abstract class AvailableShippingOption implements Parcelable {


  public abstract String description();

  public abstract ShippingLevel shippingLevel();

  @Nullable //Why would this be null
  public abstract BasicAmount shippingAmount();

  public abstract List<CheckoutMessage> shippingErrors();
  //TODO delivery estimate


}
