package com.overstock.android.prototype.client;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.service.ProductService;
import com.overstock.android.prototype.utils.ProductDetailDeserializerUtil;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author LeeMeehan, RayConnolly Created on 09-Mar-16.
 */
public class TheOAppClient {

    private ProductService productService;

    public TheOAppClient(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new GsonBuilder().registerTypeAdapter(ProductDetail.class, new ProductDetailDeserializerUtil()).create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(context.getString(R.string.ostk_base_rest_url)).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        productService = retrofit.create(ProductService.class);

    }

    public ProductService getClient() {
        return productService;
    }
}
