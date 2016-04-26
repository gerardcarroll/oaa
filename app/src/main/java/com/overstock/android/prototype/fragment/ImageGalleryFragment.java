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
import com.overstock.android.prototype.model.ProductImages;
import com.overstock.android.prototype.presenter.ImageGalleryPresenter;
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

    private Product product;

    private String baseImageUrl;

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
        baseImageUrl = this.getActivity().getString(R.string.cdn_img_url);
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
        if (productDetail.getProductImages().isEmpty() || productDetail.getProductImages().size() == 1) {
          populateImageSlider(null, productDetail.getImageLarge());
        }
        else {
          populateImageSlider(productDetail.getProductImages(), null);
        }
    }

    public void populateImageSlider(List<ProductImages> productImages, String productImage) {
        TextSliderView textSliderView;
        if (productImages != null) {
            pagerIndicator.setTotalNumberOfPages(productImages.size());
            for (ProductImages image : productImages) {
                textSliderView = new TextSliderView(this.getActivity().getApplicationContext());
                Log.d(TAG, "Passing " + baseImageUrl + image.getImagePath() + " to image slider to be displayed");
                textSliderView.image(baseImageUrl + image.getImagePath())
                        .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
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
            textSliderView = new TextSliderView(this.getActivity().getApplicationContext());
            Log.d(TAG, "Passing " + baseImageUrl + productImage + " to image slider to be displayed");
            textSliderView.image(baseImageUrl + productImage).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            sliderLayout.addSlider(textSliderView);
            sliderLayout.setCustomAnimation(new CustomDescriptionAnimation());
            sliderLayout.stopAutoCycle();
        }
    }
}
