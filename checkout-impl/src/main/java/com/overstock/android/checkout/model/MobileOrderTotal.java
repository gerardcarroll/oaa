package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import java.math.BigDecimal;

@AutoValue
public abstract class MobileOrderTotal {

  public static Builder builder(){
    return new AutoValue_MobileOrderTotal.Builder();
  }

  public abstract BigDecimal clubORedemptionAmt();

  public abstract BigDecimal couponSavings();

  public abstract BigDecimal giftCardCredit();

  public abstract BigDecimal grandTotal();

  public abstract BigDecimal grandTotalLessShipping();

  public abstract BigDecimal instoreCredit();

  public abstract BigDecimal promotionalSavings();

  public abstract BigDecimal shippingCharge();

  public abstract BigDecimal subtotal();

  public abstract BigDecimal tax();

  public abstract BigDecimal totalSavings();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder clubORedemptionAmt(BigDecimal cluboRedemptionAmount);
    public abstract Builder couponSavings(BigDecimal couponSavings);
    public abstract Builder giftCardCredit(BigDecimal giftCardCredit);
    public abstract Builder grandTotal(BigDecimal grandTotal);
    public abstract Builder grandTotalLessShipping(BigDecimal grandTotalLessShipping);
    public abstract Builder instoreCredit(BigDecimal instoreCredit);
    public abstract Builder promotionalSavings(BigDecimal promotionalSavings);
    public abstract Builder shippingCharge(BigDecimal shippingCharge);
    public abstract Builder subtotal(BigDecimal subtotal);
    public abstract Builder tax(BigDecimal tax);
    public abstract Builder totalSavings(BigDecimal totalSavings);

    public abstract MobileOrderTotal build();
  }
}
