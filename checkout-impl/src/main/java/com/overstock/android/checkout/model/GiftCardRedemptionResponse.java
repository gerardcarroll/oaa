package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import android.support.annotation.Nullable;

import java.util.List;

@AutoValue
public abstract class GiftCardRedemptionResponse {

  public static Builder builder(){
    return new AutoValue_GiftCardRedemptionResponse.Builder();
  }

  public abstract List<GiftCardRedemptionError> errors();

  public abstract List<GiftCardRedemptionError> giftCardErrors();

  @Nullable
  public abstract MobileOrderTotal mobileOrderTotals();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder errors(List<GiftCardRedemptionError> errors);
    public abstract Builder giftCardErrors(List<GiftCardRedemptionError> giftCardErrors);
    public abstract Builder mobileOrderTotals(MobileOrderTotal mobileOrderTotal);

    public abstract GiftCardRedemptionResponse build();
  }
}
