package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.view.ProductDetailView;

/**
 * Created by rconnolly on 3/21/2016.
 */
public interface ProductDetailPresenter {

  void setView(final ProductDetailView productDetailView);

  void onDestroy();
}
