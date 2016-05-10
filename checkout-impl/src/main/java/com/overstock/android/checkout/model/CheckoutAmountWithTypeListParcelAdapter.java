package com.overstock.android.checkout.model;

import com.ryanharter.auto.value.parcel.TypeAdapter;

import android.os.Parcel;

public class CheckoutAmountWithTypeListParcelAdapter implements TypeAdapter<CheckoutAmountWithTypeList> {

  @Override
  public CheckoutAmountWithTypeList fromParcel(Parcel in) {
    CheckoutAmountWithTypeList list = new CheckoutAmountWithTypeList();
    in.readTypedList(list,CheckoutAmountWithType.CREATOR);
    return list;
  }

  @Override
  public void toParcel(CheckoutAmountWithTypeList value, Parcel dest) {
    dest.writeTypedList(value);
  }

}
