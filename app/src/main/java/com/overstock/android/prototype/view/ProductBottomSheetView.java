package com.overstock.android.prototype.view;

import java.util.ArrayList;

import com.overstock.android.prototype.model.Options;

/**
 * @author LeeMeehan Created on 13-Apr-16.
 * @since Created on 13-Apr-16.
 */
public interface ProductBottomSheetView {

  void updateSpinner(final ArrayList<Options> options);

  void displayToast(final String message);

  void updateQuantityIndicator(int newQuantity);

  void updateFinalPrice(final float finalPrice);

  void handleQuantityIcons(final int currentQuantity, final int maxQuantityAllowed);

  void updateRewardsAmount(final float rewardAmount);

  void toggleSpinner();

}
