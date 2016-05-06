package com.overstock.android.prototype.service;

import android.support.annotation.Nullable;

import com.parse.ParseUser;

/**
 * Created by gcarroll on 05/05/2016.
 */
public class CheckOutCoordinatorImpl implements CheckOutCoordinator {
  @Override
  public void checkOut() {

  }

  @Override
  @Nullable
  public String getCartIdForParseUser() {
    return ParseUser.getCurrentUser().getString("cart_id");
  }

  @Override
  @Nullable
  public String getOstkCustIdForParseUser() {
    return ParseUser.getCurrentUser().getString("ostk_customer_id");
  }

  @Override
  @Nullable
  public String getOstkAddressIdForParseUser() {
    return ParseUser.getCurrentUser().getString("ostk_customer_addr_id");
  }

}
