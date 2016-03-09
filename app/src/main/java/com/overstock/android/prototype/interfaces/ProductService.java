package com.overstock.android.prototype.interfaces;

import com.overstock.android.prototype.models.ProductsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rConnolly on 3/8/2016.
 */
public interface ProductService {

    @GET("search.json?keywords=nfl&count=10")
    Observable<ProductsResponse> getBestSellers(@Query("sort") String sort);

    @GET("search.json?keywords=nfl&count=10")
    Observable<ProductsResponse> getNewArrivals(@Query("sort") String sort);

}
