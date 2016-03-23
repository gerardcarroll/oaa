package com.overstock.android.prototype.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.view.ProductDetailView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author RayConnolly Created on 21-03-2016
 */
public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

  private static final String TAG = ProductDetailActivity.class.getName();

  @Inject
  ProductDetailPresenter presenter;

  @Bind(R.id.product_detail_product_name)
  TextView productName;

  @Bind(R.id.product_detail_product_price)
  TextView productPrice;

  @Bind(R.id.product_detail_content)
  TextView productDescription;

  @Bind(R.id.product_detail_toolbar)
  Toolbar toolbar;

  private int id;


  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((OAppPrototypeApplication) this.getApplication()).getComponent().inject(this);
    setContentView(R.layout.activity_product_detail);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("");

    final Bundle extras = getIntent().getExtras();
    final byte[] b = extras.getByteArray("image");
    id = extras.getInt("id");
    final String name = extras.getString("name");
    final String price = extras.getString("price");

    // TODO optomize image load using Picasso
    final Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
    final ImageView image = (ImageView) findViewById(R.id.product_detail_activity_shared_image_1);
    image.setImageBitmap(bmp);

    //productId.setText(String.valueOf(id));
    productName.setText(name);
    productPrice.setText(price);

    presenter.setView(this);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {

    final int id = item.getItemId();

    if (id == R.id.action_settings || id == R.id.action_refresh || id == R.id.action_logout) {
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void displayProductDetails(String description) {
    Log.d(TAG, "Product Details description" + description.toString());
    productDescription.setText(description.toString());
  }
}
