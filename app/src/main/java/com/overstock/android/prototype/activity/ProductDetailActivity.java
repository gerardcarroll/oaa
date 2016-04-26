package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.fragment.HorizontialScrollFragment;
import com.overstock.android.prototype.fragment.ImageGalleryFragment;
import com.overstock.android.prototype.fragment.ProductBottomSheetFragment;
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

  private static final String BASE_IMAGE_URL = "http://ak1.ostkcdn.com/images/products/";

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

  @Bind(R.id.product_detail_toolbar)
  Toolbar toolbar;

  @Bind(R.id.btn_buy)
  FloatingActionButton btn_buy;

//  @Bind(R.id.slider)
//  SliderLayout sliderLayout;
//
//  @Bind(R.id.custom_Indicator)
//  PageNumberIndicator pagerIndicator;

  private ImageGalleryFragment fragment = null;

  private android.support.v4.app.FragmentManager manager = null;

  private android.support.v4.app.FragmentTransaction ft;

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ApplicationComponent.Initializer.init(this.getApplication()).inject(this);
    setContentView(R.layout.activity_product_detail);
    ButterKnife.bind(this);

    final Bundle extras = getIntent().getExtras();
    final Product product = Parcels.unwrap(extras.getParcelable("parcel"));

    initImageGalleryFragment();

    productName.setText(product.getName());
    final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    productPrice
        .setText(this.getString(R.string.product_price_fmt, currencyCode, String.valueOf(product.getMemberPrice())));
    //sliderLayout.stopAutoCycle();
    presenter.setView(this);
    presenter.retrieveProductDetails(product.getId());
  }

  @OnClick(R.id.btn_buy)
  public void expandBottom_sheet() {
    ProductDetail productDetails = presenter.getProductDetails();
    if (productDetails != null) {
      ProductBottomSheetFragment productBottomSheetFragment = new ProductBottomSheetFragment();
      Bundle bundle = new Bundle();
      bundle.putParcelable("productDetails", Parcels.wrap(productDetails));
      productBottomSheetFragment.setArguments(bundle);
      productBottomSheetFragment.show(getSupportFragmentManager(), productBottomSheetFragment.getTag());
    }
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

//    if (productDetail.getProductImages().isEmpty() || productDetail.getProductImages().size() == 1) {
//      populateImageSlider(null, productDetail.getImageLarge());
//    }
//    else {
//      populateImageSlider(productDetail.getProductImages(), null);
//    }
    btn_buy.setVisibility(View.VISIBLE);
  }

//  private void populateImageSlider(List<ProductImages> productImages, String largeImage) {
//    TextSliderView textSliderView;
//    if (productImages != null) {
//      pagerIndicator.setTotalNumberOfPages(productImages.size());
//      for (ProductImages image : productImages) {
//        textSliderView = new TextSliderView(this);
//        Log.d(TAG, "Passing " + BASE_IMAGE_URL + image.getImagePath() + " to image slider to be displayed");
//        textSliderView.image(BASE_IMAGE_URL + image.getImagePath())
//            .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
//        sliderLayout.addSlider(textSliderView);
//      }
//      sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
//      sliderLayout.setCustomAnimation(new CustomDescriptionAnimation());
//      sliderLayout.addOnPageChangeListener(new PageChangeListener() {
//        @Override
//        public void onPageSelected(int position) {
//          if (pagerIndicator != null) {
//            pagerIndicator.setCurrentPageNumber(position + 1);
//          }
//        }
//      });
//      sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
//      sliderLayout.setOnTouchListener(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//          sliderLayout.stopAutoCycle();
//          return true;
//        }
//      });
//
//    }
//    else {
//      textSliderView = new TextSliderView(this);
//      Log.d(TAG, "Passing " + BASE_IMAGE_URL + largeImage + " to image slider to be displayed");
//      textSliderView.image(BASE_IMAGE_URL + largeImage).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
//      sliderLayout.addSlider(textSliderView);
//      sliderLayout.setCustomAnimation(new CustomDescriptionAnimation());
//      sliderLayout.stopAutoCycle();
//    }
//  }

  @Override
  public void addHorizontalRecyclerView(int layoutResourceId, ArrayList<Product> products, String displayText) {
    Log.d(TAG, "Passing " + displayText + " products to adapter to be displayed. List size : " + products.size());
    Fragment frag = getSupportFragmentManager().findFragmentByTag(HorizontialScrollFragment.TAG);
    if (frag == null) {
      this.getSupportFragmentManager().beginTransaction().add(layoutResourceId,
        HorizontialScrollFragment.newInstance(products, displayText), HorizontialScrollFragment.TAG).commit();
    }
    else {
      this.getSupportFragmentManager().beginTransaction().replace(layoutResourceId,
        HorizontialScrollFragment.newInstance(products, displayText), HorizontialScrollFragment.TAG).commit();
    }
  }

  private void initImageGalleryFragment() {
    if (manager == null)
      manager = getSupportFragmentManager();
    if (manager.findFragmentById(R.id.image_gallery_fragment_container) == null) {
      fragment = new ImageGalleryFragment();
      ft = manager.beginTransaction();
      ft.add(R.id.image_gallery_fragment_container, fragment).commit();
    }
  }
}
