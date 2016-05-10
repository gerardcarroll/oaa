package com.overstock.android.checkout.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.overstock.android.prototype.gson.HrefWrapper;

import java.util.List;

@AutoValue
public abstract class CheckoutItemsResponse implements Parcelable {

  public static Builder builder(){
    return new AutoValue_CheckoutItemsResponse.Builder();
  }

  public abstract Meta meta();

  @SerializedName("list")
  public abstract List<CheckoutItem> items();

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder meta(Meta meta);
    public abstract Builder items(List<CheckoutItem> items);
    public abstract CheckoutItemsResponse build();
  }

  @AutoValue
  public static abstract class Meta implements Parcelable {

    public static Builder builder(){
      return new AutoValue_CheckoutItemsResponse_Meta.Builder();
    }

    public abstract String hash();

    @SerializedName("@self")
    public abstract HrefWrapper self();

    @AutoValue.Builder
    public static abstract class Builder {
      public abstract Builder hash(String hash);
      public abstract Builder self(HrefWrapper hrefWrapper);
      public abstract Meta build();
    }
  }
}
