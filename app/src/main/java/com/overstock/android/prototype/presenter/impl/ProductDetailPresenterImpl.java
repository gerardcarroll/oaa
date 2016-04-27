package com.overstock.android.prototype.presenter.impl;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.view.ProductDetailView;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author RayConnolly, LeeMeehan Created on 3/21/2016.
 */
public class ProductDetailPresenterImpl implements ProductDetailPresenter {

  private static final String TAG = ProductDetailPresenterImpl.class.getName();

  private ProductDetail productDetails;

  private Subscription subscription = Subscriptions.empty();

  private ProductDetailView productDetailView;

  private final ProductDataService productDataService;

  private final Context context;

  @Inject
  public ProductDetailPresenterImpl(final Application applicationContext, final ProductDataService productDataService) {
    this.productDataService = productDataService;
    this.context = applicationContext;
  }

  @Override
  public void setView(ProductDetailView productDetailView) {
    this.productDetailView = productDetailView;
  }

  @Override
  public void retrieveProductDetails(final Integer productId) {
    if (productDetailView == null) {
      subscription.unsubscribe();
    }
    else {
      refresh(productId);
    }
  }

  @Override
  public ProductDetail getProductDetails() {
    if (productDetails != null) {
      return productDetails;
    }
    return null;
  }

  @Override
  public void onDestroy() {
    productDetailView = null;
    subscription.unsubscribe();
  }

  private void refresh(final Integer productId) {
    subscription = productDataService.getProductDetails(productId).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductDetail>() {
          @Override
          public void onCompleted() {
            Log.d(TAG, "COMPLETED, Finished loading Product Details");
          }

          @Override
          public void onError(Throwable e) {
            Log.w(TAG, "FAILURE, Failed to load Product Details");
          }

          @Override
          public void onNext(ProductDetail productDetail) {
            Log.d(TAG, "SUCCESS, Product Details successfully loaded");
            productDetails = productDetail;

              productDetailView.addImageGalleryFragment();
              productDetailView.addProductDetailsSummaryFragment(productDetail);
          }
        });
  }

}
