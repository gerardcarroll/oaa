package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.ProductDetailsSummaryView;

/**
 * Created by rconnolly on 4/27/2016.
 */
public interface ProductDetailsSummaryPresenter {
    void setView(ProductDetailsSummaryView productDetailsSummaryView) ;
    void addSimilarItemsRecyclerView();
    void removeView();
}
