package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

import com.overstock.android.gson.HrefWrapper;

import android.os.Parcelable;

@AutoValue
public abstract class Checkouts implements Parcelable{

  public static Builder builder(){
    return new AutoValue_Checkouts.Builder();
  }

  public abstract Meta meta();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder meta(Meta meta);
    public abstract Checkouts build();
  }

  @AutoValue
  public abstract static class Meta implements Parcelable{

    public static Builder builder(){
      return new AutoValue_Checkouts_Meta.Builder();
    }

    @SerializedName("@self")
    public abstract HrefWrapper self();

    @SerializedName("@create")
    public abstract HrefWrapper create();

    @AutoValue.Builder
    public static abstract class Builder {
      public abstract Builder self(HrefWrapper self);
      public abstract Builder create(HrefWrapper create);
      public abstract Meta build();
    }
  }
}
