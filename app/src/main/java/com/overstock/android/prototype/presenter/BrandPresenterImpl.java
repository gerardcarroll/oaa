package com.overstock.android.prototype.presenter;

import java.util.ArrayList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

import android.util.Log;

import com.overstock.android.prototype.interfaces.TheOAppClient;
import com.overstock.android.prototype.models.Product;
import com.overstock.android.prototype.models.ProductDataService;
import com.overstock.android.prototype.models.ProductsResponse;
import com.overstock.android.prototype.view.BrandView;

import javax.inject.Inject;

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

    productDataService.getProducts().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ProductsResponse>() {
          @Override
          public void onCompleted() {}

          @Override
          public void onError(Throwable e) {
            Log.i("HI THERE", "FAIL");
          }

          @Override
          public void onNext(ProductsResponse productsResponse) {
            Log.i("HI THERE", "SUCCESS");
            brandView.displayBestSellers((ArrayList<Product>) productsResponse.getProducts().getProductsList());
            brandView.displayNewArrivals((ArrayList<Product>) productsResponse.getProducts().getProductsList());
          }
        });

  }
}
