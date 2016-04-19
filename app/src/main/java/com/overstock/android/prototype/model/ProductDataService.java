package com.overstock.android.prototype.model;

import android.content.UriMatcher;
import android.net.Uri;
import android.util.Log;

import com.overstock.android.prototype.interfaces.OappProvider;
import com.overstock.android.prototype.provider.OappProviderContract;
import com.overstock.android.prototype.service.ProductService;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author LeeMeehan, rayConnolly Created on 09-Mar-16.
 */
public class ProductDataService implements OappProvider<ProductsResponse> {

  protected static final String TAG = ProductDataService.class.getName();

  public static final int ProductEntry_BESTSELLERS = 100;

  public static final int ProductEntry_NEWARRIVALS = 101;

  protected static final String BEST_SELLERS = "recommended";

  protected static final String NEW_ARRIVALS = "New+Arrivals";

  // The URI Matcher used by this content provider.
  protected static final UriMatcher sUriMatcher = buildUriMatcher();

  protected ProductService productService;

  @Inject
  public ProductDataService(final ProductService productService) {
    this.productService = productService;
  }

  @Override
  public Observable<ProductsResponse> query(Uri uri) {

    switch (sUriMatcher.match(uri)) {
      case ProductEntry_BESTSELLERS:
        return productService.query(uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS),
          BEST_SELLERS, Integer.valueOf(uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT)));
      case ProductEntry_NEWARRIVALS:
        return productService.query(uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS),
          NEW_ARRIVALS, Integer.valueOf(uri.getQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT)));
      default:
        throw new UnsupportedOperationException("Unknown uri: " + uri);
    }
  }

  protected static UriMatcher buildUriMatcher() {

    final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    final String authority = OappProviderContract.CONTENT_AUTHORITY;

    // add all endpoints that will be serviced
    matcher.addURI(authority, OappProviderContract.PRODUCTS_PATH + "/" + OappProviderContract.ProductEntry.BEST_SELLERS,
      ProductEntry_BESTSELLERS);
    matcher.addURI(authority, OappProviderContract.PRODUCTS_PATH + "/" + OappProviderContract.ProductEntry.NEW_ARRIVALS,
      ProductEntry_NEWARRIVALS);

    return matcher;
  }

  public Observable<ProductDetail> getProductDetails(final Integer productId) {
    Log.d(TAG, "Getting Observable ProductDetail from client");
    return productService.getProductDetails(productId);
  }

}
