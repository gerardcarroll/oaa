package com.overstock.android.prototype.models;

import android.util.Log;

import com.overstock.android.prototype.interfaces.ProductService;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author LeeMeehan, RayConnolly Created on 09-Mar-16.
 */
public class ProductDataService {
  private static final String TAG = ProductDataService.class.getName();

  private ProductService productService;

  private String bestSellers = "recommended";

  private String newArrivals = "new+arrivals";

  @Inject
  public ProductDataService(final ProductService productService) {
    this.productService = productService;
  }

  public Observable<ProductsResponse> getBestSellers() {
    Log.d(TAG, "Getting Observable ProductResponse from client.");
    return productService.getBestSellers(bestSellers);
  }

  public Observable<ProductsResponse> getNewArrivals() {
    Log.d(TAG, "Getting Observable ProductResponse from client.");
    return productService.getNewArrivals(newArrivals);
  }

}
