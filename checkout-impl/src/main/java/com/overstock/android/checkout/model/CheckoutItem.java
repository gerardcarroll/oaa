package com.overstock.android.checkout.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;
import com.overstock.android.prototype.gson.HrefWrapper;

import java.util.List;

@AutoValue
public abstract class CheckoutItem implements Parcelable {

  public static Builder builder(){
    return new AutoValue_CheckoutItem.Builder();
  }

  private CheckoutItem warrantyItem;

  @SerializedName("__type")
  public abstract String type();

  public abstract String id();

  public abstract Meta meta();

  public abstract Product product();

  public abstract int quantity();

  @Nullable
  public abstract ShippingLevel shippingLevel();

  public abstract ItemTotal totals();

  @Nullable
  public abstract List<String> coveredItemIds();

  @Nullable
  public CheckoutItem getWarrantyItem(){
    return warrantyItem;
  }

  public void setWarrantyItem(CheckoutItem warrantyItem){
    this.warrantyItem = warrantyItem;
  }

  //TODO wishListItem, financingPromotion

  @Nullable
  public abstract String giftNote();

  public Builder toBuilder(){
    return CheckoutItem.builder()
      .id(id())
      .coveredItemIds(coveredItemIds())
      .meta(meta())
      .quantity(quantity())
      .totals(totals())
      .giftNote(giftNote())
      .product(product())
      .type(type())
      .shippingLevel(shippingLevel());
  }

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder type(String type);
    public abstract Builder id(String id);
    public abstract Builder meta(Meta meta);
    public abstract Builder product(Product product);
    public abstract Builder quantity(int quantity);
    public abstract Builder shippingLevel(ShippingLevel shippingLevel);
    public abstract Builder totals(ItemTotal itemTotal);
    public abstract Builder coveredItemIds(List<String> coveredItemIds);
    public abstract Builder giftNote(String giftNote);
    public abstract CheckoutItem build();
  }

  @AutoValue
  public static abstract class Meta implements Parcelable {

    public static Builder builder(){
      return new AutoValue_CheckoutItem_Meta.Builder();
    }

    @Nullable
    public abstract List<AvailableShippingOption> availableShippingOptions();

    @SerializedName("@self")
    public abstract HrefWrapper self();

    @AutoValue.Builder
    public static abstract class Builder {
      public abstract Builder availableShippingOptions(List<AvailableShippingOption> availableShippingOptions);
      public abstract Builder self(HrefWrapper wrapper);
      public abstract Meta build();
    }
  }

  @AutoValue
  public static abstract class Product implements Parcelable {

    public static Builder builder(){
      return new AutoValue_CheckoutItem_Product.Builder();
    }

    public abstract long id();

    public abstract long optionId();

    public abstract Meta meta();

    @AutoValue.Builder
    public static abstract class Builder{
      public abstract Builder id(long id);
      public abstract Builder optionId(long optionId);
      public abstract Builder meta(Meta meta);
      public abstract Product build();
    }

    @AutoValue
    public static abstract class Meta implements Parcelable {

      public static Builder builder(){
        return new AutoValue_CheckoutItem_Product_Meta.Builder();
      }

      public abstract String thumbImage();

      public abstract String mainImage();

      public abstract String name();

      public abstract String optionDescription();

      @Nullable
      public abstract Marketplace marketplace();

      @AutoValue.Builder
      public static abstract class Builder {
        public abstract Builder thumbImage(String thumbImage);
        public abstract Builder mainImage(String mainImage);
        public abstract Builder name(String name);
        public abstract Builder optionDescription(String optionDescription);
        public abstract Builder marketplace(Marketplace marketplace);
        public abstract Meta build();
      }
    }
  }

  @AutoValue
  public abstract static class ItemTotal implements Parcelable {

    public static Builder builder(){
      return new AutoValue_CheckoutItem_ItemTotal.Builder();
    }

    public abstract ItemTotalAmounts amounts();

    @AutoValue.Builder
    public static abstract class Builder{
      public abstract Builder amounts(ItemTotalAmounts amounts);
      public abstract ItemTotal build();
    }
  }

  @AutoValue
  public abstract static class ItemTotalAmounts implements Parcelable{

    public static Builder builder(){
      return new AutoValue_CheckoutItem_ItemTotalAmounts.Builder();
    }

    public abstract CheckoutAmount subTotalAmount();
    public abstract CheckoutAmount unitPriceAmount();

    @Nullable
    public abstract CheckoutAmount shippingAmount();

    @Nullable
    public abstract CheckoutAmount promotionalAmount();

    @Nullable
    public abstract CheckoutAmount couponAmount();

    @AutoValue.Builder
    public static abstract class Builder{
      public abstract Builder subTotalAmount(CheckoutAmount amount);
      public abstract Builder unitPriceAmount(CheckoutAmount amount);
      public abstract Builder shippingAmount(CheckoutAmount amount);
      public abstract Builder promotionalAmount(CheckoutAmount amount);
      public abstract Builder couponAmount(CheckoutAmount amount);
      public abstract ItemTotalAmounts build();
    }

  }

  @AutoValue
  public abstract static class Marketplace implements Parcelable{

    public abstract String vendorName();
  }

}
