package com.overstock.android.prototype.models;

import javax.inject.Inject;

import rx.Observable;

import com.overstock.android.prototype.interfaces.ProductService;

/**
 * @author LeeMeehan, rayConnolly Created on 09-Mar-16.
 */
public class ProductDataService {

  private ProductService productService;

  @Inject
  public ProductDataService(final ProductService productService) {
    this.productService = productService;
  }

  public Observable<ProductsResponse> getProducts() {
    return productService.getProducts();
  }

}
