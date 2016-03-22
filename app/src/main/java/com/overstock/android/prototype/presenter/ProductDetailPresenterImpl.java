package com.overstock.android.prototype.presenter;

import android.util.Log;

import com.overstock.android.prototype.models.ProductDataService;
import com.overstock.android.prototype.models.ProductDetail;
import com.overstock.android.prototype.view.ProductDetailView;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by rconnolly on 3/21/2016.
 */
public class ProductDetailPresenterImpl implements ProductDetailPresenter {

    private static final String TAG = ProductDetailPresenterImpl.class.getName();

    private Subscription subscription = Subscriptions.empty();

    private ProductDetailView productDetailView;

    private final ProductDataService productDataService;

    @Inject
    public ProductDetailPresenterImpl(final ProductDataService productDataService) {
        this.productDataService = productDataService;
    }

    @Override
    public void setView(ProductDetailView productDetailView) {
        this.productDetailView = productDetailView;
        if (productDetailView == null){
            subscription.unsubscribe();
        } else {
            refresh();
        }
    }

    @Override
    public void onDestroy() {
        productDetailView = null;
        subscription.unsubscribe();
    }

    private void refresh() {
        subscription = productDataService.getProductDetails().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductDetail>() {
                    @Override
                    public void onCompleted() {
                        Log.i("COMPLETED", "Finished loading Product Details");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("FAILURE", "Failed to load Product Details");
                    }

                    @Override
                    public void onNext(ProductDetail productDetail) {
                        Log.i("SUCCESS", " Product Details successfully loaded");
                        productDetailView.displayProductDetails(productDetail.getDescription());
                    }
                });
    }
}
