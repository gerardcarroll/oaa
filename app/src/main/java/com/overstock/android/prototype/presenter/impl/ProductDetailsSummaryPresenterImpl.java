package com.overstock.android.prototype.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.model.ProductsResponse;
import com.overstock.android.prototype.presenter.ProductDetailsSummaryPresenter;
import com.overstock.android.prototype.provider.OappProviderContract;
import com.overstock.android.prototype.view.ProductDetailsSummaryView;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rconnolly on 4/27/2016.
 */
public class ProductDetailsSummaryPresenterImpl implements ProductDetailsSummaryPresenter {

    ProductDetailsSummaryView productDetailsSummaryView ;
    private final ProductDataService productDataService;
    private final Context context;

    @Inject
    public ProductDetailsSummaryPresenterImpl( Context context, ProductDataService productDataService) {
        this.productDataService = productDataService;
        this.context = context;
    }

    @Override
    public void setView(ProductDetailsSummaryView productDetailsSummaryView) {
        this.productDetailsSummaryView  = productDetailsSummaryView ;
    }

    @Override
    public void removeView() {
        this.productDetailsSummaryView = null;
    }

    @Override
    public void addSimilarItemsRecyclerView(){
        // TODO: change call to recommendation service to provide an actual list of similar products to the currently
        // selected product
        productDataService.query(OappProviderContract.ProductEntry.buildProductBestsellerUri()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductsResponse>() {
            @Override
            public void onCompleted() {
                Log.i("COMPLETED", "Finished loading Similar Products");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("FAILURE", "Failed to load Similar Products");
            }

            @Override
            public void onNext(ProductsResponse productsResponse) {
                Log.i("SUCCESS",
                        "Similar Products successfully loaded " + productsResponse.getProducts().getProductsList().size());
                productDetailsSummaryView.addHorizontalRecyclerView(R.id.similar_products_hrv,
                        (ArrayList<Product>) productsResponse.getProducts().getProductsList(),
                        context.getString(R.string.similar_producst_labels));
            }
        });
    }
}
