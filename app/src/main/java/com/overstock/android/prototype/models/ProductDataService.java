package com.overstock.android.prototype.models;

import javax.inject.Inject;

import rx.Observable;

import android.util.Log;

import com.overstock.android.prototype.interfaces.ProductService;

/**
 * @author LeeMeehan, rayConnolly Created on 09-Mar-16.
 */
public class ProductDataService {
  private static final String TAG = ProductDataService.class.getName();

  private ProductService productService;

  @Inject
  public ProductDataService(final ProductService productService) {
    this.productService = productService;
  }

  public Observable<ProductsResponse> getProducts() {
    Log.d(TAG, "Getting Observable ProductResponse from client.");
    return productService.getProducts();
  }

}
