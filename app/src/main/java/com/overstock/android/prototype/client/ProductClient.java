package com.overstock.android.prototype.client;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * Created by rconnolly on 3/9/2016.
 */
public class ProductClient {

    private Context context;

    String ENDPOINT = "http://www.overstock.com/api/";

    String imageUrl;

    //@Bind(R.id.ivProductImage)
    ImageView productImage;

    //@Bind(R.id.pbProductImage)
    ProgressBar progressBar;

    //@Bind(R.id.tvProductName)
    TextView productName;

    //@Bind(R.id.tvProductPrice)
    TextView productPrice;

    public ProductClient(Context context){
        this.context = context;
    }

//    public Call<ProductsResponse> getProducts(){
//
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ENDPOINT)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ProductService service = retrofit.create(ProductService.class);
//
//        // Call<ProductsResponse> products = service.getProducts("iPod");
//        Call<ProductsResponse> products = service.getProducts();
//
//        products.enqueue(new Callback<ProductsResponse>() {
//            @Override
//            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
//
//                Log.i("Products", " statusCode: " + response.code());
//
//                if (response.isSuccess()) {
//                    ProductsResponse result = response.body();
//
//                    for (Product product : result.getProducts().getProductsList()) {
//
//                        imageUrl = String.format("http://ak1.ostkcdn.com/images/products/%s", product.getImageMedium1());
//
//                        progressBar.setVisibility(View.VISIBLE);
//                        Picasso.with(context).load(imageUrl).
//                                into(productImage, new com.squareup.picasso.Callback() {
//                                    @Override
//                                    public void onSuccess() {
//
//                                        if (progressBar != null) {
//                                            progressBar.setVisibility(View.GONE);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError() {
//                                    }
//                                });
//                        productName.setText(product.getName());
//                        productPrice.setText(product.getMemberPrice().toString());
//
//                        Log.i("Products Image ", imageUrl);
//                        Log.i("Products Name ", product.getName());
//                        Log.i("Products Price ", product.getMemberPrice().toString());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ProductsResponse> call, Throwable t) {
//                Log.e("Call failed", t.toString());
//            }
//        });
//
//        return products;
//    }

}
