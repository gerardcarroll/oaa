package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.interfaces.ProductService;
import com.overstock.android.prototype.models.Product;

import java.util.List;

import butterknife.Bind;
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

    @Bind(R.id.ivProductImage)
    ImageView productImage;

    @Bind(R.id.tvProductName)
    TextView productName;

    @Bind(R.id.tvProductPrice)
    TextView productPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_brand);

        requestFeed();
    }

    private void requestFeed(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductService service = retrofit.create(ProductService.class);

        Call<List<Product>> products = service.listProducts();

        products.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                Log.i("Products onResponse ", "statusCode" + response.code());

                if (response.isSuccess()) {
                    List<Product> result = response.body();

                    Log.i("Products", result.toString());

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Call failed", t.toString());
            }
        });

    }

}
