package com.overstock.android.checkout.model;

import com.google.gson.GsonBuilder;
import com.overstock.android.checkout.gson.CheckoutAmountWithTypeListAdapterFactory;

public class CheckoutGsonHelper {
  private CheckoutGsonHelper() {
  }

  public static GsonBuilder registerTypeAdapterFactories(GsonBuilder builder) {
    return builder
      .registerTypeAdapterFactory(AutoValue_AvailableShippingOption.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_BasicAmount.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutAmount.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutCustomer.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem_ItemTotal.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem_ItemTotalAmounts.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem_Product.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem_Product_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItem_Marketplace.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItemsResponse.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutItemsResponse_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutMessage.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutPayment.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutResponse.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutResponse_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutTotals.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_ShippingLevel.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_Checkouts.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_Checkouts_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_AndroidPayCheckoutRequest.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_AddressRef.typeAdapterFactory())
      .registerTypeAdapterFactory(new CheckoutAmountWithTypeListAdapterFactory())
      .registerTypeAdapterFactory(new CheckoutAmountWithTypeTypeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_UpdateCheckoutItemRequest.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_UpdateCheckoutItemRequest_Product.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GuestInfo.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GiftCardRedemptionResponse.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GiftCardRedemptionError.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GiftCardRedemptionRequest.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GiftCardShippingAddress.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GiftCard.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_MobileOrderTotal.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_GiftCardPayment.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_InStoreCreditPayment.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_AndroidPayPayment.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_BookOrderResponse.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_BookOrderResponse_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_BookOrderResponse_Problems.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_BookOrderResponse_Problems_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutError.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutError_Error.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_CheckoutError_Meta.typeAdapterFactory())
      .registerTypeAdapterFactory(AutoValue_ClubOPayment.typeAdapterFactory())
      ;
  }
}
