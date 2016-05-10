package com.overstock.android.checkout.model;

public interface CheckoutTotalType {
  String SUBTOTAL = "subtotalAmount";
  String PROMOTIONAL = "promotionalDiscountAmount";
  String COUPON =  "couponDiscountAmount";
  String INSTORE_CREDIT = "instoreCreditAppliedAmount";
  String GIFTCARD = "giftCardAppliedAmount";
  String CLUB_O = "clubOAppliedAmount";
  String SHIPPING = "shippingAmount";
  String TAX ="taxAmount";
}
