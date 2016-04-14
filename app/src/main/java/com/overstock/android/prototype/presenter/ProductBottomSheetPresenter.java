package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.view.ProductBottomSheetView;

/**
 * @author LeeMeehan.
 * @since Created on 13-Apr-16.
 */
public interface ProductBottomSheetPresenter {

  void setView(final ProductBottomSheetView productBottomSheetView);

  void updateProductPage(final ProductDetail productDetail);

  void addQuantity(final int existingQuantity);

  void removeQuantity(final int existingQuantity);

  void onDestroy();

  void setMaxQuantityAllowed(final int maxQuantity);

  void setCurrentPrice(float currentPrice);

  void updateFinalPrice(final int quantity);

  void setRewardsApplied(final float rewardsApplied);

  void applyDiscount(final String totalPrice, final String discount);

  void resetRewards();
}
