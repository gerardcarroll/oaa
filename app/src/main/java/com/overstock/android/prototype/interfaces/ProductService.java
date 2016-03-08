package com.overstock.android.prototype.interfaces;

import com.overstock.android.prototype.models.Product;

import java.util.List;

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

    @GET("search.json?keywords=iPod")
    Call<List<Product>> listProducts();
//    Call<List<Product>> listProducts(@Query("productType") String productType);
}
