package com.overstock.android.prototype.presenter.impl;

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

  private Subscription subscription = Subscriptions.empty();

  private ProductDetailView productDetailView;

  private final ProductDataService productDataService;

  @Inject
  public ProductDetailPresenterImpl(final ProductDataService productDataService) {
    this.productDataService = productDataService;
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
            Log.d("SUCCESS", "SUCCESS, Product Details successfully loaded");
            productDetailView.displayProductDetails(productDetail);
          }
        });
  }
}
