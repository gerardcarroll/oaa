package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.interfaces.ProductService;
import com.overstock.android.prototype.models.Product;
import com.overstock.android.prototype.models.ProductsResponse;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rconnolly on 3/8/2016.
 */
public class BrandActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_brand);

        productImage = (ImageView) findViewById(R.id.ivProductImage);
        progressBar = (ProgressBar) findViewById(R.id.pbProductImage);
        productName = (TextView) findViewById(R.id.tvProductName);
        productPrice = (TextView) findViewById(R.id.tvProductPrice);

        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService service = retrofit.create(ProductService.class);

//        Call<ProductsResponse> products = service.getProducts("iPod");
        Call<ProductsResponse> products = service.getProducts();

        products.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {

                Log.i("Products", " statusCode: " + response.code());

                if (response.isSuccess()) {
                    ProductsResponse result = response.body();

                    for (Product product : result.getProducts().getProductsList()) {

                        imageUrl = String.format("http://ak1.ostkcdn.com/images/products/%s", product.getImageLarge());

                        progressBar.setVisibility(View.VISIBLE);
                        Picasso.with(getApplicationContext()).load(imageUrl).
                            into(productImage, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {

                                    if (progressBar != null) {
                                        progressBar.setVisibility(View.GONE);
                                    }
                            }

                            @Override
                            public void onError() {
                            }
                        });
                        productName.setText(product.getName());
                        productPrice.setText(product.getMemberPrice().toString());

                        Log.i("Products Image ", imageUrl);
                        Log.i("Products Name ", product.getName());
                        Log.i("Products Price ", product.getMemberPrice().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e("Call failed", t.toString());
            }
        });
    }
    
}
