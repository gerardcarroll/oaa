package com.overstock.android.prototype.models;

import android.content.UriMatcher;
import android.net.Uri;

import com.overstock.android.prototype.interfaces.OappProvider;
import com.overstock.android.prototype.interfaces.ProductService;
import com.overstock.android.prototype.provider.OappProviderContract;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author LeeMeehan, rayConnolly Created on 09-Mar-16.
 */
public class ProductDataService implements OappProvider<ProductsResponse> {

  public static final int ProductEntry_BESTSELLERS = 100;
  public static final int ProductEntry_NEWARRIVALS = 101;

  private static final String TAG = ProductDataService.class.getName();

  // The URI Matcher used by this content provider.
  private static final UriMatcher sUriMatcher = buildUriMatcher();

  private ProductService productService;

  private String bestSellers = "recommended";
  private String newArrivals = "New+Arrivals";

  @Inject
  public ProductDataService(final ProductService productService) {
    this.productService = productService;
  }

//  public Observable<ProductsResponse> getBestSellers() {
//    Log.d(TAG, "Getting Observable ProductResponse from client.");
//    return productService.getBestSellers(bestSellers);
//  }
//
//  public Observable<ProductsResponse> getNewArrivals() {
//    Log.d(TAG, "Getting Observable ProductResponse from client.");
//    return productService.getNewArrivals(newArrivals);
//  }

  @Override
  public Observable<ProductsResponse> query(Uri uri) {

    switch(sUriMatcher.match(uri)){
      case ProductEntry_BESTSELLERS:
        return productService.query(
                uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS),
                bestSellers,
                Integer.valueOf(uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT))
        );
      case ProductEntry_NEWARRIVALS:
        return productService.query(
                uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS),
                newArrivals,
                Integer.valueOf(uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT))
        );
      default:
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
  }

  private static UriMatcher buildUriMatcher() {

    final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    final String authority = OappProviderContract.CONTENT_AUTHORITY;

    //add all endpoints that will be serviced
    matcher.addURI(authority, OappProviderContract.PRODUCTS_PATH + "/" + OappProviderContract.ProductEntry.BESTSELLERS, ProductEntry_BESTSELLERS);
    matcher.addURI(authority, OappProviderContract.PRODUCTS_PATH + "/" + OappProviderContract.ProductEntry.NEWARRIVALS, ProductEntry_NEWARRIVALS);

    return matcher;
  }


}
