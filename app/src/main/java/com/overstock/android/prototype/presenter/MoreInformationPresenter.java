package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.view.MoreInformationView;

/**
 * Created by rconnolly on 4/26/2016.
 */
public interface MoreInformationPresenter {

    void setView(final MoreInformationView moreInformationView);

    ProductDetail getProductDetails();

    //void retrieveProductDetails(final Integer productId);

    void onDestroy();
}
