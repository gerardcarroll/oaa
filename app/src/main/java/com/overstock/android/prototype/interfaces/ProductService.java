package com.overstock.android.prototype.interfaces;

import retrofit2.http.GET;
import rx.Observable;

import com.overstock.android.prototype.models.ProductsResponse;

/**
 * Created by rConnolly on 3/8/2016.
 */
public interface ProductService {

    @GET("search.json?keywords=nfl")
    Observable<ProductsResponse> getProducts();
}
