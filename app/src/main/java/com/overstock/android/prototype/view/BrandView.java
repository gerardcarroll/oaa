package com.overstock.android.prototype.view;

import com.overstock.android.prototype.model.Product;

import java.util.ArrayList;

/**
 * @author LeeMeehan Created on 07-Mar-16.
 */
public interface BrandView {

  void addHorizontialRecyclerView(final int layoutResourceId,final ArrayList<Product> parcelables, final String displayText);

}
