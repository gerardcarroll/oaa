package com.overstock.android.prototype.utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.Log;

import com.overstock.android.prototype.model.OViewerImage;

/**
 * Util class to handle image display quality depending on the device the user in operating on.
 *
 * @author LeeMeehan
 * @since Created on 26-Apr-16.
 */
public class ProductImageUtil {

  private static final String TAG = ProductImageUtil.class.getName();

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
  @NonNull
  public List<String> getOptimizedImages(final List<OViewerImage> oViewerImages) {
    final List<String> imagesPaths = new ArrayList<>();
    final int imageSizeId = getSizeIndex();
    for (int i = 0; i < oViewerImages.size(); i++) {
      if (oViewerImages.get(i).getImageSizes().isEmpty()) {
        imagesPaths.add(oViewerImages.get(i).getOriginalImagePath());
      }
      else if (imageSizeId >= 0 && imageSizeId < oViewerImages.get(i).getImageSizes().size()) {
        imagesPaths.add(oViewerImages.get(i).getImageSizes().get(imageSizeId).getImagePath());
      }
      else
        Log.d(TAG, "Image exists but resolution not suitable quality for current display");
    }
    return imagesPaths;
  }

  /**
   * Will return a zero indexed number that corresponds to a image size selection in the oViewer list of image sizes.
   *
   * @return sizeId.
   */
  private int getSizeIndex() {
    int screenLayout = context.getResources().getConfiguration().screenLayout;
    screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
    int sizeIndex;
    switch (screenLayout) {
      case Configuration.SCREENLAYOUT_SIZE_SMALL:
        Log.d(TAG, "Device Size Small.");
        sizeIndex = 0;
        break;
      case Configuration.SCREENLAYOUT_SIZE_NORMAL:
        Log.d(TAG, "Device Size NORMAL.");
        sizeIndex = 1;
        break;
      case Configuration.SCREENLAYOUT_SIZE_LARGE:
        Log.d(TAG, "Device Size LARGE.");
        sizeIndex = 2;
        break;
      case Configuration.SCREENLAYOUT_SIZE_XLARGE:
        Log.d(TAG, "Device Size XLARGE.");
        sizeIndex = 2;
        break;
      default:
        Log.d(TAG, "Device Size Unknown. Returning default value.");
        sizeIndex = 1;
    }
    Log.d(TAG, "Returning image size id: " + sizeIndex);
    return sizeIndex;
  }

}
