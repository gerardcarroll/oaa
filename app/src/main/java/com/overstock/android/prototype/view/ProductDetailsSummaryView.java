package com.overstock.android.prototype.view;

import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDetail;

import java.util.ArrayList;

/**
 * Created by rconnolly on 4/27/2016.
 */
public interface ProductDetailsSummaryView {
    void displayProductDetails(final ProductDetail productDetail);
    void addHorizontalRecyclerView(int layoutResourceId, ArrayList<Product> products, String displayText);
}
