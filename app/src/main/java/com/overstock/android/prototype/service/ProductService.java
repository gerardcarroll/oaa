package com.overstock.android.prototype.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import com.overstock.android.prototype.models.ProductDetail;
import com.overstock.android.prototype.models.ProductsResponse;

/**
 * @author RayConnolly Created on 3/8/2016.
 */
public interface ProductService {

  String PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS = "keywords";

  String PRODUCT_SERVICE_QUERY_PARAM_NAME_SORT = "sort";

  String PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT = "count";

  @GET("search.json?keywords=nfl&count=30")
  Observable<ProductsResponse> getBestSellers(@Query("sort")
  final String sortValue);

  @GET("search.json?keywords=football+jersey&count=30")
  Observable<ProductsResponse> getNewArrivals(@Query("sort")
  final String sortOrder);

  @GET("search.json")
  Observable<ProductsResponse> query(@Query(PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS)
  final String keywords, @Query(PRODUCT_SERVICE_QUERY_PARAM_NAME_SORT)
  final String sortOrder, @Query(PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT)
  final int count);

  @GET("product.json")
  Observable<ProductDetail> getProductDetails(@Query("prod_id")
  final Integer productId);

}
