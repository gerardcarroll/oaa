package com.overstock.android.prototype.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.fragment.HorizontialScrollFragment;
import com.overstock.android.prototype.fragment.ProductBottomSheetFragment;
import com.overstock.android.prototype.listener.TransitionListener;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.view.ProductDetailView;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author RayConnolly, LeeMeehan Created on 21-03-2016
 */
public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

  private static final String TAG = ProductDetailActivity.class.getName();

  @Inject
  ProductDetailPresenter presenter;

  @Inject
  Picasso picasso;

  @Bind(R.id.product_detail_product_name)
  TextView productName;

  @Bind(R.id.product_detail_product_price)
  TextView productPrice;

  @Bind(R.id.product_detail_content)
  WebView productDescription;

  @Bind(R.id.product_detail_activity_shared_image_1)
  ImageView productImage;

  @Bind(R.id.product_detail_toolbar)
  Toolbar toolbar;

  @Bind(R.id.btn_buy)
  FloatingActionButton btn_buy;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ApplicationComponent.Initializer.init(this.getApplication()).inject(this);
    setContentView(R.layout.activity_product_detail);
    ButterKnife.bind(this);

    final Bundle extras = getIntent().getExtras();
    final Product product = Parcels.unwrap(extras.getParcelable("parcel"));
    final Bitmap receivedImage = extras.getParcelable("image");
    productImage.setImageBitmap(receivedImage);

    this.getWindow().getSharedElementEnterTransition().addListener(new TransitionListener() {
      @Override
      public void onTransitionEnd(Transition transition) {
        Log.d(TAG, "Updating Image.");
        picasso.load(getString(R.string.product_img_base_url) + product.getImageLarge()).fit().error(R.drawable.product_placeholder)
            .noPlaceholder().into(productImage);
      }
    });

    productName.setText(product.getName());
    final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    productPrice.setText(this.getString(R.string.product_price_fmt, currencyCode, String.valueOf(product.getMemberPrice())));

    presenter.setView(this);
    presenter.retrieveProductDetails(product.getId());
  }

  @OnClick(R.id.btn_buy)
  public void expandBottom_sheet() {
    ProductBottomSheetFragment productBottomSheetFragment = new ProductBottomSheetFragment();
    productBottomSheetFragment.show(getSupportFragmentManager(), productBottomSheetFragment.getTag());
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
  public void displayProductDetails(final ProductDetail productDetail) {
    Log.d(TAG, "Displaying Product Details." + productDetail.toString());
    productDescription.loadData(productDetail.getDescription().trim(), getString(R.string.webview_html_encoding), null);
  }

  @Override
  public void addHorizontialRecyclerView(int layoutResourceId, ArrayList<Product> products, String displayText) {
      Log.d(TAG, "Passing " + displayText + " products to adapter to be displayed. List size : " + products.size());
      this.getSupportFragmentManager()
              .beginTransaction()
              .add(layoutResourceId, HorizontialScrollFragment.newInstance(products, displayText),HorizontialScrollFragment.TAG)
              .commit();
  }
}
