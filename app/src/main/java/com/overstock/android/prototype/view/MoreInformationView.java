package com.overstock.android.prototype.view;

import com.overstock.android.prototype.model.ProductDetail;

/**
 * Created by rconnolly on 4/26/2016.
 */
public interface MoreInformationView {

    void displayDetails(ProductDetail productDetail);

    void displayShipping();
}
