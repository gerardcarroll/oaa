package com.overstock.android.checkout.model;

import com.google.auto.value.AutoValue;

import com.overstock.android.account.model.AddressResponse;
import com.overstock.android.account.model.TemporaryAddressResponse;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@AutoValue
public abstract class AndroidPayCheckoutRequest implements CheckoutRequest {

  public static AndroidPayCheckoutRequest from(CheckoutResponse lastResponse,
    @NonNull AddressRef billingAddress, @NonNull AddressRef shippingAddress){

    return from(lastResponse)
      .billingAddress(billingAddress)
      .shippingAddress(shippingAddress)
      .build();
  }

  public static AndroidPayCheckoutRequest from(CheckoutResponse checkoutResponse, ClubOPayment clubOPayment){
    CheckoutPayment payment;

    if(checkoutResponse.payment() != null){
      payment = checkoutResponse.payment().toBuilder().clubOPayment(clubOPayment).build();
    } else {
      payment = CheckoutPayment.builder().clubOPayment(clubOPayment).build();
    }

    return from(checkoutResponse)
      .payment(payment)
      .build();
  }


  public static AndroidPayCheckoutRequest from(CheckoutResponse checkoutResponse, GiftCardPayment giftCardPayment) {
    CheckoutPayment payment;
    if (checkoutResponse.payment() != null) {
      payment = checkoutResponse.payment().toBuilder().giftCardPayment(giftCardPayment).build();
    } else {
      payment = CheckoutPayment.builder().giftCardPayment(giftCardPayment).build();
    }
    return from(checkoutResponse)
      .payment(payment)
      .build();
  }

  public static AndroidPayCheckoutRequest from(CheckoutResponse checkoutResponse, AndroidPayPayment androidPayPayment) {
    CheckoutPayment payment;
    if (checkoutResponse.payment() != null) {
      payment = checkoutResponse.payment().toBuilder().androidPayPayment(androidPayPayment).build();
    } else {
      payment = CheckoutPayment.builder().androidPayPayment(androidPayPayment).build();
    }

    return from(checkoutResponse)
      .payment(payment)
      .build();
  }

  public static AndroidPayCheckoutRequest from(@NonNull TemporaryAddressResponse billingAddress, @NonNull TemporaryAddressResponse shippingAddress,
    @NonNull GuestInfo guestInfo) {
    return builder()
      .billingAddress(AddressRef.from(billingAddress))
      .shippingAddress(AddressRef.from(shippingAddress))
      .guestInfo(guestInfo)
      .orderSource(ORDER_SOURCE)
      .build();
  }

  public static AndroidPayCheckoutRequest from(@NonNull TemporaryAddressResponse billingAddress, @NonNull TemporaryAddressResponse shippingAddress) {
    return builder().billingAddress(AddressRef.from(billingAddress)).shippingAddress(AddressRef.from(shippingAddress)).guestInfo(null).build();
  }

  public static Builder from(CheckoutResponse checkoutResponse) {
    AndroidPayCheckoutRequest.Builder builder = builder()
      .id(checkoutResponse.id())
      .itemsId(checkoutResponse.itemsId())
      .invoiceId(checkoutResponse.invoiceId())
      .billingAddress(checkoutResponse.billingAddress())
      .shippingAddress(checkoutResponse.shippingAddress())
      .payment(checkoutResponse.payment());

    if (checkoutResponse.guestInfo() != null) {
      builder.guestInfo(checkoutResponse.guestInfo());
    } else if (checkoutResponse.customer() != null) {
      builder.customer(checkoutResponse.customer());
    }

    return builder;
  }

  static Builder builder() {
    return new AutoValue_AndroidPayCheckoutRequest.Builder().orderSource(ORDER_SOURCE).payment(CheckoutPayment.builder().build());
  }

  @Nullable
  public abstract String id();

  @Nullable
  public abstract CheckoutCustomer customer();

  @Nullable
  public abstract String itemsId();

  @Nullable
  public abstract String invoiceId();

  public abstract AddressRef billingAddress();

  public abstract AddressRef shippingAddress();

  @Nullable
  public abstract GuestInfo guestInfo();

  @Nullable
  public abstract CheckoutPayment payment();

  public abstract String orderSource();

  public Builder toBuilder() {
    return new AutoValue_AndroidPayCheckoutRequest.Builder(this);
  }


  @AutoValue.Builder
  public static abstract class Builder {
    abstract Builder orderSource(String orderSource);

    abstract Builder id(String id);

    abstract Builder itemsId(String itemsId);

    abstract Builder invoiceId(String invoiceId);

    abstract Builder billingAddress(AddressRef billingAddress);

    abstract Builder shippingAddress(AddressRef shippingAddress);

    abstract Builder guestInfo(GuestInfo guestInfo);

    abstract Builder customer(CheckoutCustomer customer);

    public abstract Builder payment(CheckoutPayment payment);

    public abstract AndroidPayCheckoutRequest build();
  }
}