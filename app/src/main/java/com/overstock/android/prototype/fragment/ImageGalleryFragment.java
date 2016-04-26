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
import com.overstock.android.prototype.listener.PageChangeListener;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Product;
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

    private static final String BASE_IMAGE_URL = "http://ak1.ostkcdn.com/images/products/";

    @Bind(R.id.slider)
    SliderLayout sliderLayout;

    @Bind(R.id.custom_Indicator)
    PageNumberIndicator pagerIndicator;

    @Inject
    ImageGalleryPresenter imageGalleryPresenter;

    public ImageGalleryFragment() {}

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_gallery, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        final Product product = new Product();

        imageGalleryPresenter.setView(this);
        imageGalleryPresenter.retrieveProductDetails(product.getId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
        imageGalleryPresenter.onDestroy();
    }

    @Override
    public void displayImages(List<ProductImages> productImages, String productImage) {
        TextSliderView textSliderView;
        if (productImages != null) {
            pagerIndicator.setTotalNumberOfPages(productImages.size());
            for (ProductImages image : productImages) {
                textSliderView = new TextSliderView(this.getActivity().getApplicationContext());
                Log.d(TAG, "Passing " + BASE_IMAGE_URL + image.getImagePath() + " to image slider to be displayed");
                textSliderView.image(BASE_IMAGE_URL + image.getImagePath())
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
            Log.d(TAG, "Passing " + BASE_IMAGE_URL + productImage + " to image slider to be displayed");
            textSliderView.image(BASE_IMAGE_URL + productImage).setScaleType(BaseSliderView.ScaleType.FitCenterCrop);
            sliderLayout.addSlider(textSliderView);
            sliderLayout.setCustomAnimation(new CustomDescriptionAnimation());
            sliderLayout.stopAutoCycle();
        }
    }
}
