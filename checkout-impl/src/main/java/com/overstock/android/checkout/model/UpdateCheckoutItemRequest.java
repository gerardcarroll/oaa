package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

import android.os.Parcelable;
import android.support.annotation.Nullable;

@AutoValue
public abstract class UpdateCheckoutItemRequest implements Parcelable {

  public static Builder builder() {
    return new AutoValue_UpdateCheckoutItemRequest.Builder();
  }

  public static Builder from(CheckoutItem item) {
    return builder().type(item.type()).id(item.id()).giftNote(item.giftNote()).quantity(item.quantity()).product(Product.from(item.product()))
      .shippingLevel(item.shippingLevel());
  }

  @SerializedName("__type")
  public abstract String type();
  public abstract String id();

  public abstract Product product();

  public abstract int quantity();

  public abstract ShippingLevel shippingLevel();

  @Nullable
  public abstract String giftNote();

  public Builder toBuilder() {
    return new AutoValue_UpdateCheckoutItemRequest.Builder(this);
  }

  //TODO financingPromotion wishListItem

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder id(String id);

    public abstract Builder product(Product product);

    public abstract Builder quantity(int quantity);

    public abstract Builder shippingLevel(ShippingLevel shippingLevel);

    public abstract Builder giftNote(String giftNote);

    public abstract Builder type(String type);

    public abstract UpdateCheckoutItemRequest build();
  }

  @AutoValue
  public static abstract class Product implements Parcelable {
    public static Product from(CheckoutItem.Product product) {
      return new AutoValue_UpdateCheckoutItemRequest_Product(product.id(), product.optionId());
    }

    public abstract long id();

    public abstract long optionId();
  }
}
