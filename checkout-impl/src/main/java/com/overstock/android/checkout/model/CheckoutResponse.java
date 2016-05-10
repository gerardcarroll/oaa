package com.overstock.android.checkout.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.overstock.android.prototype.gson.HrefWrapper;

import java.util.List;

@AutoValue
public abstract class CheckoutResponse implements Parcelable {

  public static Builder builder(){
    return new AutoValue_CheckoutResponse.Builder();
  }

  public abstract Meta meta();

  public abstract String id();

  public abstract String invoiceId();

  public abstract String itemsId();

  @Nullable
  public abstract CheckoutCustomer customer();

  public abstract CheckoutPayment payment();

  public abstract CheckoutTotals totals();

  @Nullable
  public abstract GuestInfo guestInfo();

  @Nullable
  public abstract AddressRef billingAddress();

  @Nullable
  public abstract AddressRef shippingAddress();

  //TODO giftInformation, csrUserName, shippingEstimatePostalCode

  public abstract String orderSource();

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder meta(Meta meta);
    public abstract Builder id(String id);
    public abstract Builder invoiceId(String invoiceId);
    public abstract Builder itemsId(String itemsId);
    public abstract Builder customer(CheckoutCustomer checkoutCustomer);
    public abstract Builder payment(CheckoutPayment payment);
    public abstract Builder totals(CheckoutTotals totals);
    public abstract Builder guestInfo(GuestInfo guestInfo);
    public abstract Builder billingAddress(AddressRef addressRef);
    public abstract Builder shippingAddress(AddressRef addressRef);
    public abstract Builder orderSource(String orderSource);
    public abstract CheckoutResponse build();
  }


  @AutoValue
  public abstract static class Meta implements Parcelable {

    public static Builder builder(){
      return new AutoValue_CheckoutResponse_Meta.Builder();
    }

    public abstract String status();

    public abstract String hash();

    @SerializedName("@self")
    public abstract HrefWrapper self();

    @SerializedName("@book")
    public abstract HrefWrapper book();

    @SerializedName("@items")
    public abstract HrefWrapper items();

    @SerializedName("@analytics")
    public abstract HrefWrapper analytics();

    @SerializedName("@fraud")
    public abstract HrefWrapper fraud();

    @SerializedName("@affiliate")
    public abstract HrefWrapper affiliate();

    @SerializedName("@campaign")
    public abstract HrefWrapper campaign();

    public abstract List<CheckoutMessage> infoMessages();

    public abstract List<CheckoutMessage> errorMessages();


    @AutoValue.Builder
    public static abstract class Builder{
      public abstract Builder status(String status);
      public abstract Builder hash(String hash);
      public abstract Builder self(HrefWrapper self);
      public abstract Builder book(HrefWrapper book);
      public abstract Builder items(HrefWrapper items);
      public abstract Builder analytics(HrefWrapper analytics);
      public abstract Builder fraud(HrefWrapper fraud);
      public abstract Builder affiliate(HrefWrapper affiliate);
      public abstract Builder campaign(HrefWrapper campaign);
      public abstract Builder infoMessages(List<CheckoutMessage> infoMessages);
      public abstract Builder errorMessages(List<CheckoutMessage> errorMessages);
      public abstract Meta build();
    }
  }



}
