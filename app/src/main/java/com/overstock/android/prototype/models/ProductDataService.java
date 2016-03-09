package com.overstock.android.prototype.models;

import com.overstock.android.prototype.interfaces.ProductService;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author LeeMeehan, rayConnolly Created on 09-Mar-16.
 */
public class ProductDataService {

  private ProductService productService;

  private String bestSellers = "Recommended";

  private String newArrivals = "New Arrivals";

  @Inject
  public ProductDataService(final ProductService productService) {
    this.productService = productService;
  }

  public Observable<ProductsResponse> getBestSellers() {
    return productService.getBestSellers(bestSellers);
  }

  public Observable<ProductsResponse> getNewArrivals() {
    return productService.getNewArrivals(newArrivals);
  }

}
