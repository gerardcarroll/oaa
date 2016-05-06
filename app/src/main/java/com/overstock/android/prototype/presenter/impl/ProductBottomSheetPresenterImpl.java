package com.overstock.android.prototype.presenter.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import android.util.Log;

import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductBottomSheetPresenter;
import com.overstock.android.prototype.service.CheckOutCoordinator;
import com.overstock.android.prototype.view.ProductBottomSheetView;

/**
 * Presenter Implementation that handles all logical processes that are present on the product details checkout form. It
 * also controls the execution methods that are used to display info on the ui on the ProductBottomSheetView.
 * 
 * @author LeeMeehan
 * @since 14-Apr-16.
 */
public class ProductBottomSheetPresenterImpl implements ProductBottomSheetPresenter {

  private static final String TAG = ProductDetailPresenterImpl.class.getName();

  private CheckOutCoordinator checkOutCoordinator;

  private ProductBottomSheetView productBottomSheetView;

  private float currentPrice;

  private int maxQuantityAllowed;

  private float rewardsApplied;

  @Inject
  public ProductBottomSheetPresenterImpl(final CheckOutCoordinator checkOutCoordinator){
    this.checkOutCoordinator = checkOutCoordinator;
  }

  @Override
  public void setView(final ProductBottomSheetView productBottomSheetView) {
    this.productBottomSheetView = productBottomSheetView;
  }

  @Override
  public void onDestroy() {
    productBottomSheetView = null;
  }

  @Override
  public void setMaxQuantityAllowed(int maxQuantity) {
    this.maxQuantityAllowed = maxQuantity;
  }

  @Override
  public void setCurrentPrice(float currentPrice) {
    this.currentPrice = currentPrice;
  }

  @Override
  public void setRewardsApplied(final float rewardsApplied) {
    this.rewardsApplied = rewardsApplied;
  }

  @Override
  public void applyDiscount(String totalPrice, String discount) {
    final String placeHolderText = "0.00";
    productBottomSheetView.updateRewardsAmount(Float.valueOf(placeHolderText));
    currentPrice = (Float.parseFloat(totalPrice) - Float.parseFloat(discount));
    productBottomSheetView.updateFinalPrice(currentPrice);
  }

  @Override
  public void resetRewards() {
    productBottomSheetView.updateRewardsAmount(rewardsApplied);
  }

  @Override
  public void initiateCheckout() {
    Log.d(TAG, "initiating checkout");
    String userCartId = checkOutCoordinator.getCartIdForParseUser();
    Log.d(TAG, "Users Cart Id is : " + userCartId);
  }

  @Override
  public void updateProductPage(final ProductDetail productDetail) {
    final ArrayList<Options> options = (ArrayList<Options>) productDetail.getOptions();
    productBottomSheetView.updateFinalPrice(productDetail.getMemberPrice());
    currentPrice = productDetail.getMemberPrice();
    if (options.size() > 1) {
      productBottomSheetView.updateSpinner((ArrayList<Options>) options.clone());
    }
    else
      productBottomSheetView.toggleSpinner();
    maxQuantityAllowed = options.get(0).getMaxQuantityAllowed();
  }

  @Override
  public void addQuantity(final int existingQuantity) {
    if (existingQuantity < maxQuantityAllowed) {
      Integer newQuantity = existingQuantity + 1;
      productBottomSheetView.updateQuantityIndicator(newQuantity);
      updateFinalPrice(newQuantity);
      productBottomSheetView.handleQuantityIcons(newQuantity, maxQuantityAllowed);
    }
    else
      productBottomSheetView.displayToast("We cannot allow you to order any more than this amount sorry.");
  }

  @Override
  public void updateFinalPrice(final int quantity) {
    float newPrice = currentPrice * quantity;
    productBottomSheetView.updateFinalPrice(newPrice);
  }

  @Override
  public void removeQuantity(final int existingQuantity) {
    if (existingQuantity > 1) {
      Integer newQuantity = existingQuantity - 1;
      productBottomSheetView.updateQuantityIndicator(newQuantity);
      updateFinalPrice(newQuantity);
      productBottomSheetView.handleQuantityIcons(newQuantity, maxQuantityAllowed);
    }
  }

}
