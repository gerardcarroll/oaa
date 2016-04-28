package com.overstock.android.prototype.presenter.impl;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ImageGalleryPresenter;
import com.overstock.android.prototype.view.ImageGalleryView;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author RayConnolly, created on 4/25/2016.
 */
public class ImageGalleryPresenterImpl implements ImageGalleryPresenter {

    private static final String TAG = ImageGalleryPresenterImpl.class.getName();

    private ImageGalleryView imageGalleryView;

    private ProductDetail productDetails;

    private Subscription subscription = Subscriptions.empty();

    private final ProductDataService productDataService;

    private Context context;

    @Inject
    public ImageGalleryPresenterImpl(final Application applicationContext, final ProductDataService productDataService) {
        this.productDataService = productDataService;
        this.context = applicationContext;
    }

    @Override
    public void setView(ImageGalleryView imageGalleryView) {
        this.imageGalleryView = imageGalleryView;
    }

    @Override
    public void retrieveProductDetails(Integer productId) {
        if (imageGalleryView == null) {
            subscription.unsubscribe();
        }
        else {
            refresh(productId);
        }
    }

    @Override
    public ProductDetail getProductDetails() {
        if (productDetails != null) {
            return productDetails;
        }
        return null;
    }

    @Override
    public void onDestroy() {
        imageGalleryView = null;
    }

    private void refresh(final Integer productId) {
        subscription = productDataService.getProductDetails(productId).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductDetail>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "COMPLETED, Finished loading Product Details");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "FAILURE, Failed to load Product Details");
                    }

                    @Override
                    public void onNext(ProductDetail productDetail) {
                        Log.d(TAG, "SUCCESS, Product Details successfully loaded");
                        productDetails = productDetail;
                        imageGalleryView.displayImages(productDetail);
                    }
                });
    }
}
