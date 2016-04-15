package com.overstock.android.prototype.view;

import com.overstock.android.prototype.model.Product;

import java.util.ArrayList;

/**
 * Created by itowey on 14/04/16.
 */
public interface CommonViews {
    void addHorizontialRecyclerView(final int layoutResourceId,final ArrayList<Product> parcelables, final String displayText);
}
