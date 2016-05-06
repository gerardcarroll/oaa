package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.animatorutils.CustomDescriptionAnimation;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.listener.PageChangeListener;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ImageGalleryPresenter;
import com.overstock.android.prototype.utils.ProductImageUtil;
import com.overstock.android.prototype.view.ImageGalleryView;
import com.overstock.android.prototype.widgets.PageNumberIndicator;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author RayConnolly, created on 4/25/2016.
 */
public class ImageGalleryFragment extends Fragment implements ImageGalleryView {

  private static final String TAG = ImageGalleryFragment.class.getName();

  public static final String PRODUCT_DETAILS_PARCEL = "PRODUCT_DETAILS_PARCEL";

  @Inject
  ImageGalleryPresenter imageGalleryPresenter;

  @Bind(R.id.slider)
  SliderLayout sliderLayout;

  @Bind(R.id.custom_Indicator)
  PageNumberIndicator pagerIndicator;

  @Inject
  ProductImageUtil productImageUtil;

  private Product product;

  public ImageGalleryFragment() {}

  public static ImageGalleryFragment newInstance(Product product) {
    ImageGalleryFragment fragment = new ImageGalleryFragment();
    Bundle args = new Bundle();
    args.putParcelable(PRODUCT_DETAILS_PARCEL, product);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ApplicationComponent.Initializer.init(this.getActivity().getApplication()).inject(this);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_image_gallery, container, false);
    if (getArguments() != null) {
      product = getArguments().getParcelable(PRODUCT_DETAILS_PARCEL);
    }
    ButterKnife.bind(this, view);

    sliderLayout.stopAutoCycle();
    imageGalleryPresenter.setView(this);
    imageGalleryPresenter.retrieveProductDetails(product.getId());
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    imageGalleryPresenter.onDestroy();
  }

  @Override
  public void displayImages(ProductDetail productDetail) {
    if (productDetail.getoViewerImages().isEmpty() || productDetail.getoViewerImages().size() == 1) {
      populateImageSlider(null, productDetail.getImageLarge());
    }
    else {
      List<String> productImages = productImageUtil.getOptimizedImages(productDetail.getoViewerImages());
      populateImageSlider(productImages, null);
    }
  }

  private void populateImageSlider(List<String> productImages, String largeImage) {
    TextSliderView textSliderView;
    final String baseImageUrl = this.getResources().getString(R.string.cdn_img_base_url);
    if (productImages != null) {
      pagerIndicator.setTotalNumberOfPages(productImages.size());
      for (String imagePath : productImages) {
        textSliderView = new TextSliderView(this.getContext());
        Log.d(TAG, "Passing " + baseImageUrl + imagePath + " to image slider to be displayed");
        textSliderView.image(baseImageUrl + imagePath).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
        sliderLayout.addSlider(textSliderView);
      }
      sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
      sliderLayout.setCustomAnimation(new CustomDescriptionAnimation());
      sliderLayout.addOnPageChangeListener(new PageChangeListener() {
        @Override
        public void onPageSelected(int position) {
          if (pagerIndicator != null) {
            pagerIndicator.setCurrentPageNumber(position + 1);
          }
        }
      });
      sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
      sliderLayout.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          sliderLayout.stopAutoCycle();
          return true;
        }
      });
    }
    else {
      textSliderView = new TextSliderView(this.getContext());
      Log.d(TAG, "Passing " + baseImageUrl + largeImage + " to image slider to be displayed");
      textSliderView.image(baseImageUrl + largeImage).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
      sliderLayout.addSlider(textSliderView);
      sliderLayout.setCustomAnimation(new CustomDescriptionAnimation());
      sliderLayout.stopAutoCycle();
    }
  }
}
