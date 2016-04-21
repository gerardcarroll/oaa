package com.overstock.android.prototype.presenter.impl;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

import android.util.Log;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.model.ProductsResponse;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.provider.OappProviderContract;
import com.overstock.android.prototype.view.BrandView;

/**
 * @author LeeMeehan Created on 07-Mar-16.
 */
public class BrandPresenterImpl implements BrandPresenter {
  private static final String TAG = BrandPresenterImpl.class.getName();

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
    if (brandView == null) {
      subscription.unsubscribe();
    }
    else {
      refresh();
    }
  }

  @Override
  public void onDestroy() {
    brandView = null;
    subscription.unsubscribe();
  }

  public void refresh() {

    subscription = productDataService.query(OappProviderContract.ProductEntry.buildProductBestsellerUri())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ProductsResponse>() {
          @Override
          public void onCompleted() {
            Log.d(TAG, "ProductDataService.GetProduct has no more data to emit.");
          }

          @Override
          public void onError(Throwable e) {
            Log.e(TAG, "Error on subscribing to ProductDataService.GetProducts");
          }

          @Override
          public void onNext(ProductsResponse productsResponse) {
            Log.d(TAG, "Next value on subscribing to ProductDataService.GetProducts "
              + productsResponse.getProducts().getProductsList().size());
            brandView.addHorizontalRecyclerView(R.id.best_sellers_hrv,
              (ArrayList<Product>) productsResponse.getProducts().getProductsList(), "Best Sellers");
          }
        });

    productDataService.query(OappProviderContract.ProductEntry.buildProductNewArrivalsUri())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
            Log.i("SUCCESS",
              "New Arrivals successfully loaded " + productsResponse.getProducts().getProductsList().size());
            brandView.addHorizontalRecyclerView(R.id.new_arrivals_hrv,
              (ArrayList<Product>) productsResponse.getProducts().getProductsList(), "New Arrivals");
          }
        });

  }
}
