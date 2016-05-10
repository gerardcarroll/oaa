package com.overstock.android.checkout.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.overstock.android.prototype.model.AddressResponse;
import com.overstock.android.prototype.model.TemporaryAddressResponse;

@AutoValue
public abstract class AddressRef implements Parcelable {
  public static AddressRef from(TemporaryAddressResponse address) {
    return builder().type(AddressType.TEMPORARY).id(address.id()).build();
  }

  public static AddressRef from(AddressResponse address) {
    return builder().type(AddressType.ADDRESS_BOOK).id(Long.toString(address.meta().id())).build();
  }

  static Builder builder() {
    return new AutoValue_AddressRef.Builder();
  }

  @Nullable
  public abstract String id();

  @Nullable
  public abstract String uuId();

  public abstract String type();

  @AutoValue.Builder
  abstract static class Builder {
    abstract Builder id(String id);

    abstract Builder type(String type);

    abstract Builder uuId(String uuId);

    abstract AddressRef build();
  }
}
