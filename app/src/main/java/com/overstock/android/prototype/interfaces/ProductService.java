package com.overstock.android.prototype.interfaces;

import com.overstock.android.prototype.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by rconnolly on 3/8/2016.
 */
public interface ProductService {

    @Headers(
            {
                    "Accept: application/json",
                    "Content-Type: application/json"
            }
    )

//    @GET("search.json?keywords={productType}")
//    Call<ProductsResponse> getProducts(@Query("productType") String productType);

    @GET("search.json?keywords=iPod")
    Call<ProductsResponse> getProducts();

    // TODO add call to retrieve the 10 best selling products
}
