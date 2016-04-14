package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.view.ProductDetailView;

/**
 * @author RayConnolly created on 3/21/2016.
 */
public interface ProductDetailPresenter {

  void setView(final ProductDetailView productDetailView);

  void retrieveProductDetails(final Integer productId);

  ProductDetail getProductDetails();

  void onDestroy();
}
