package com.overstock.android.prototype.interfaces;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author LeeMeehan, RayConnolly Created on 09-Mar-16.
 */
public class TheOAppClient {

  private static ProductService productService;

  private static String ENDPOINT = "http://www.overstock.com/api/";

  public static ProductService getClient() {
    if (productService == null) {
      OkHttpClient okHttpClient = new OkHttpClient();
      Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDPOINT).client(okHttpClient)
          .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create())
          .build();
      productService = retrofit.create(ProductService.class);
    }
    return productService;
  }
}
