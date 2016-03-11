package com.overstock.android.prototype.view;

import com.overstock.android.prototype.models.Product;

import java.util.ArrayList;

/**
 * @author LeeMeehan Created on 07-Mar-16.
 */
public interface BrandView {

  void displayBestSellers(final ArrayList<Product> products);

  void displayNewArrivals(final ArrayList<Product> products);

}
