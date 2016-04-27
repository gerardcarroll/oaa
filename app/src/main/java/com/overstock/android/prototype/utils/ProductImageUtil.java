package com.overstock.android.prototype.utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.overstock.android.prototype.model.OViewerImages;

/**
 * Util class to handle image display quality depending on the device the user in operating on.
 *
 * @author LeeMeehan
 * @since Created on 26-Apr-16.
 */
public class ProductImageUtil {

  public static final String TAG = ProductImageUtil.class.getName();

  private Context context;

  @Inject
  public ProductImageUtil(final Application applicationContext) {
    this.context = applicationContext;
  }

  /**
   * This method with select and retrieve the urls of the best image depending on your device size.
   *
   * @param oViewerImages a list of viewer objects.
   * @return Image Urls.
   */
  public List<String> getOptimizedImages(List<OViewerImages> oViewerImages) {
    List<String> imagesPaths = new ArrayList<>();
    final int ImageSizeId = getSizeId();
    for (int i = 0; i < oViewerImages.size(); i++) {
      imagesPaths.add(oViewerImages.get(i).getImageSizes().get(ImageSizeId).getImagePath());
    }
    return imagesPaths;
  }

  /**
   * Will return a zero indexed number that corresponds to a image size selection in the oViewer list of image sizes.
   *
   * @return sizeId.
   */
  private int getSizeId() {
    int screenLayout = context.getResources().getConfiguration().screenLayout;
    screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

    switch (screenLayout) {
      case Configuration.SCREENLAYOUT_SIZE_SMALL:
        Log.d(TAG, "Device Size Small. Returning image size id: 0");
        return 0;
      case Configuration.SCREENLAYOUT_SIZE_NORMAL:
        Log.d(TAG, "Device Size NORMAL. Returning image size id: 1");
        return 1;
      case Configuration.SCREENLAYOUT_SIZE_LARGE:
        Log.d(TAG, "Device Size LARGE. Returning image size id: 2");
        return 2;
      case Configuration.SCREENLAYOUT_SIZE_XLARGE:
        Log.d(TAG, "Device Size XLARGE. Returning image size id: 2");
        return 2;
      default:
        Log.d(TAG, "Device Size Unknown. Returning default value. Returning default value 1.");
        return 1;
    }
  }

}
