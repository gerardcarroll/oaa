package com.overstock.android.prototype.view;

import com.overstock.android.prototype.model.ProductImages;

import java.util.List;

/**
 * @author RayConnolly, created on 4/25/2016.
 */
public interface ImageGalleryView {

    void displayImages(final List<ProductImages> galleryProductImages, final String productImage);
}
