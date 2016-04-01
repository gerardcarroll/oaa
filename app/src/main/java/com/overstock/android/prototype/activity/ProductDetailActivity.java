package com.overstock.android.prototype.activity;

import javax.inject.Inject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.view.ProductDetailView;

/**
 * @author RayConnolly Created on 21-03-2016
 */
public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

  private static final String TAG = ProductDetailActivity.class.getName();

  private static final String BASE_IMAGE_URL = "http://ak1.ostkcdn.com/images/products/";

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

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ApplicationComponent.Initializer.init(this.getApplication()).inject(this);
    setContentView(R.layout.activity_product_detail);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("");

    // TODO Send product as a package.
    final Bundle extras = getIntent().getExtras();
    final byte[] b = extras.getByteArray("image");
    final Integer product_Id = extras.getInt("id");
    final String name = extras.getString("name");
    final String price = extras.getString("price");

    // TODO optimize image load using Picasso
    final Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
    final ImageView image = (ImageView) findViewById(R.id.product_detail_activity_shared_image_1);
    image.setImageBitmap(bmp);

    productName.setText(name);
    productPrice.setText(price);

    presenter.setView(this);
    presenter.retrieveProductDetails(product_Id);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
    presenter.onDestroy();
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
  public void displayProductDetails(ProductDetail productDetail) {
    //Log.d(TAG, "Product Details description" + description.toString());
    productDescription.setText(Html.fromHtml(productDetail.getDescription()));
//    final ImageView image = (ImageView) findViewById(R.id.product_detail_activity_shared_image_1);
//    Picasso.with(this).load(BASE_IMAGE_URL + productDetail.getImageMedium1())
//            .error(R.drawable.product_placeholder).into(image);

  }
}
