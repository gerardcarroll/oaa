package com.overstock.android.prototype.presenter.impl;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.MoreInformationPresenter;
import com.overstock.android.prototype.view.MoreInformationView;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by rconnolly on 4/26/2016.
 */
public class MoreInformationPresenterImpl implements MoreInformationPresenter {

    private static final String TAG = MoreInformationPresenterImpl.class.getName();

    private MoreInformationView moreInformationView;

    private ProductDetail productDetails;

    private Subscription subscription = Subscriptions.empty();

    private final ProductDataService productDataService;

    private Context context;

    @Inject
    public MoreInformationPresenterImpl(final Application applicationContext, final ProductDataService productDataService) {
        this.productDataService = productDataService;
        this.context = applicationContext;
    }

    @Override
    public void setView(MoreInformationView moreInformationView) {
        this.moreInformationView = moreInformationView;
    }

    @Override
    public ProductDetail getProductDetails() {
        if (productDetails != null) {
            return productDetails;
        }
        return null;
    }

    @Override
    public void retrieveProductDetails(Integer productId) {
        if (moreInformationView == null) {
            subscription.unsubscribe();
        }
        else {
            refresh(productId);
        }
    }

    @Override
    public void onDestroy() {
        moreInformationView = null;
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
                        moreInformationView.displayDetails(productDetail);
                    }
                });
    }
}
