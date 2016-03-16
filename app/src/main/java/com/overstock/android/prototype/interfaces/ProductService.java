package com.overstock.android.prototype.interfaces;

import com.overstock.android.prototype.models.ProductsResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rConnolly on 3/8/2016.
 */
public interface ProductService {

  @GET("search.json?keywords=nfl&count=30")
  Observable<ProductsResponse> getBestSellers(@Query("sort") String sortValue);

  @GET("search.json?keywords=football+jersey&count=30")
  Observable<ProductsResponse> getNewArrivals(@Query("sort") String sortValue);

}
