package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.view.ImageGalleryView;

/**
 * @author RayConnolly, created on 4/25/2016.
 */
public interface ImageGalleryPresenter {

    void setView(final ImageGalleryView imageGalleryView);

    ProductDetail getProductDetails();

    void retrieveProductDetails(final Integer productId);

    void onDestroy();
}
