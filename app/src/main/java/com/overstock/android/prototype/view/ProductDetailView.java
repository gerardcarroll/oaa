package com.overstock.android.prototype.view;

import com.overstock.android.prototype.model.ProductDetail;

/**
 * @author rconnolly Created on 3/21/2016.
 */
public interface ProductDetailView extends CommonViews{

  void displayProductDetails(final ProductDetail productDetail);

}
