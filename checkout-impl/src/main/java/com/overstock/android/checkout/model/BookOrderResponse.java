package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

import com.overstock.android.gson.HrefWrapper;

import android.os.Parcelable;

@AutoValue
public abstract class BookOrderResponse implements Parcelable {

  public static Builder builder(){
    return new AutoValue_BookOrderResponse.Builder();
  }

  public abstract Meta meta();

  public abstract String checkoutId();

  public abstract String checkoutStatus();

  public abstract String invoiceId();

  public abstract Problems problems();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder meta(Meta meta);
    public abstract Builder checkoutId(String checkoutId);
    public abstract Builder checkoutStatus(String checkoutStatus);
    public abstract Builder invoiceId(String invoiceId);
    public abstract Builder problems(Problems problems);

    public abstract BookOrderResponse build();
  }


  @AutoValue
  public abstract static class Meta implements Parcelable{

    public static Meta create(HrefWrapper selfWrapper){
      return new AutoValue_BookOrderResponse_Meta(selfWrapper);
    }

    @SerializedName("@self")
    public abstract HrefWrapper self();
  }

  @AutoValue
  public abstract static class Problems implements Parcelable{

    public static Problems create(Meta meta){
      return new AutoValue_BookOrderResponse_Problems(meta);
    }

    public abstract Meta meta();

    //todo list of errors

    @AutoValue
    public static abstract class Meta implements Parcelable {

      public static Meta create(HrefWrapper checkoutLinkWrapper){
        return new AutoValue_BookOrderResponse_Problems_Meta(checkoutLinkWrapper);
      }

      public abstract HrefWrapper checkoutLink();
    }
  }
}
