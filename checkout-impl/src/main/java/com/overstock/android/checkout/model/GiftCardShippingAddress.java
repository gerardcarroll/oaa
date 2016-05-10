package com.overstock.android.checkout.model;

import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.auto.value.AutoValue;

import android.os.Parcelable;

@AutoValue
public abstract class GiftCardShippingAddress implements Parcelable {

  public static String getLocationTypeFromUserAddress(UserAddress userAddress){
    if(userAddress.isPostBox()){
      return "P";
    }

    if(userAddress.getCompanyName().isEmpty()){
      return "H";
    } else {
      return "B";
    }
  }

  public static GiftCardShippingAddress create(String line1, String line2, String locationType, String city,
    String state, String zip){
    return new AutoValue_GiftCardShippingAddress(line1, line2, locationType, city, state, zip);
  }

  public abstract String line1();

  public abstract String line2();

  //This does not line up with the location type defined in
  //com.overstock.android.account/model.LocationType
  public abstract String locationType();

  public abstract String city();

  public abstract String state();

  public abstract String zip();

}
