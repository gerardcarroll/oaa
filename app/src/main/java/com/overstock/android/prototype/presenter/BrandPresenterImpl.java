package com.overstock.android.prototype.presenter;

import android.util.Log;

import com.overstock.android.prototype.models.Product;
import com.overstock.android.prototype.models.ProductDataService;
import com.overstock.android.prototype.models.ProductsResponse;
import com.overstock.android.prototype.view.BrandView;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author LeeMeehan Created on 07-Mar-16.
 */
public class BrandPresenterImpl implements BrandPresenter {
  private Subscription subscription = Subscriptions.empty();

  private BrandView brandView;

  private final ProductDataService productDataService;

  @Inject
  public BrandPresenterImpl(final ProductDataService productDataService) {
    this.productDataService = productDataService;
  }

  @Override
  public void setView(final BrandView brandView) {
    this.brandView = brandView;
    refresh();
  }

  @Override
  public void onDestroy() {
    brandView = null;
    subscription.unsubscribe();
  }

  public void refresh() {

    productDataService.getBestSellers().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ProductsResponse>() {
          @Override
          public void onCompleted() {
            Log.i("COMPLETED", "Finished loading Best Sellers");
          }

          @Override
          public void onError(Throwable e) {
            Log.i("FAILURE", "Failed to load Best Sellers");
          }

          @Override
          public void onNext(ProductsResponse productsResponse) {
            Log.i("SUCCESS", "Best Sellers successfully loaded");
            brandView.displayBestSellers((ArrayList<Product>) productsResponse.getProducts().getProductsList());
          }
        });

    productDataService.getNewArrivals().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ProductsResponse>() {
          @Override
          public void onCompleted() {
            Log.i("COMPLETED", "Finished loading New Arrivals");
          }

          @Override
          public void onError(Throwable e) {
            Log.i("FAILURE", "Failed to load New Arrivals");
          }

          @Override
          public void onNext(ProductsResponse productsResponse) {
            Log.i("SUCCESS", "New Arrivals successfully loaded");
            brandView.displayNewArrivals((ArrayList<Product>) productsResponse.getProducts().getProductsList());
          }
        });
  }
}
