package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class CheckoutMessage implements Parcelable {

  public abstract String messageId();

  public abstract String customerMessage();
}
