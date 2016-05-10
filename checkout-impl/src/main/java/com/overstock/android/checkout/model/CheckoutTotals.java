package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import com.ryanharter.auto.value.parcel.ParcelAdapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@AutoValue
public abstract class CheckoutTotals implements Parcelable {

  public static Builder builder(){
    return new AutoValue_CheckoutTotals.Builder();
  }

  public abstract BasicAmount totalAmount();

  @ParcelAdapter(CheckoutAmountWithTypeListParcelAdapter.class)
  public abstract CheckoutAmountWithTypeList amounts();

  public CheckoutAmount getSubTotalAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.SUBTOTAL);
  }

  public CheckoutAmount getCouponDiscountAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.COUPON);
  }

  public CheckoutAmount getPromotionalDiscountAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.PROMOTIONAL);
  }

  public CheckoutAmount getInstoreCreditAppliedAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.INSTORE_CREDIT);
  }

  public CheckoutAmount getGiftCardAppliedAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.GIFTCARD);
  }

  public CheckoutAmount getClubOAppliedAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.CLUB_O);
  }

  public CheckoutAmount getShippingAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.SHIPPING);
  }

  public CheckoutAmount getTaxAmount(){
    return getCheckoutAmountByType(CheckoutTotalType.TAX);
  }


  @Nullable
  private CheckoutAmount getCheckoutAmountByType(@NonNull String amountType) {
    CheckoutAmountWithTypeList amounts = amounts();
    for (int i = 0; i < amounts.size(); i++) {
      CheckoutAmountWithType amountWithType = amounts.get(i);
      if(amountType.equals(amountWithType.type())){
        return amountWithType.amount();
      }
    }
    return null;
  }

  @AutoValue.Builder
  public static abstract class Builder{
    public abstract Builder totalAmount(BasicAmount totalAmount);
    public abstract Builder amounts(CheckoutAmountWithTypeList amounts);
    public abstract CheckoutTotals build();
  }
}
