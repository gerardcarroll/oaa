package com.overstock.android.prototype.interfaces;

import com.overstock.android.prototype.models.ProductDetail;
import com.overstock.android.prototype.models.ProductsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by RayConnolly on 3/8/2016.
 */
public interface ProductService {

    public static final String PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS = "keywords";
    public static final String PRODUCT_SERVICE_QUERY_PARAM_NAME_SORT = "sort";
    public static final String PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT = "count";

    @GET("search.json?keywords=nfl&count=30")
    Observable<ProductsResponse> getBestSellers(
            @Query("sort") String sortValue
    );

    @GET("search.json?keywords=football+jersey&count=30")
    Observable<ProductsResponse> getNewArrivals(
            @Query("sort") String sortOrder
    );

    @GET("search.json")
    Observable<ProductsResponse> query(
            @Query(PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS) String keywords,
            @Query(PRODUCT_SERVICE_QUERY_PARAM_NAME_SORT) String sortOrder,
            @Query(PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT) int count
    );

  @GET("product.json?prod_id=8789213")
  Observable<ProductDetail> getProductDetails();

//  @GET("product.json?prod_id={id}")
//  Observable<ProductDetail> getProductDetails(@Query("id") String id);

}
