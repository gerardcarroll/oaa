package com.overstock.android.prototype.fragment;

import static org.junit.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Resetter;

import android.os.Build;
import android.os.Bundle;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.ProductDetail;

/**
 * @author LeeMeehan.
 * @since Created on 13-Apr-16
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class ProductBottomSheetFragmentTest {

  private static final Integer PRODUCT_DETAIL_ID = 1001;

  private static final Integer OPTION_ID = 2002;

  private static final String PRODUCT_NAME = "TEST_NAME";

  private static final String IMAGE_LARGE = "TEST_IMAGE_LARGE";

  private static final String IMAGE_MEDIUM = "TEST_IMAGE_MEDIUM";

  private static final Float PRICE = new Float(22.50);

  private static final Float OPTION_PRICE = new Float(25.50);

  private static final Integer QUANTITY = 5;

  private static final Integer OPTION_QUANTITY_ALLOWD = 7;

  private static final Integer OPTION_QUANTITY_ON_HAND = 10;

  private static final String DESCRIPTION = "TEST_DESCRIPTION";

  private static final String OPTION_DESCRIPTION = "TEST_OPTION_DESCRIPTION";

  private ProductBottomSheetFragment productBottomSheetFragment;

  @Before
  public void setUp() {
    productBottomSheetFragment = new ProductBottomSheetFragment();
    final List<Options> options = new ArrayList<>();
    final Options option = new Options(OPTION_ID, OPTION_DESCRIPTION, OPTION_QUANTITY_ON_HAND, OPTION_QUANTITY_ALLOWD,
        OPTION_PRICE);
    options.add(option);
    final ProductDetail productDetails;
    productDetails = new ProductDetail(PRODUCT_DETAIL_ID, PRODUCT_NAME, IMAGE_LARGE, IMAGE_MEDIUM, PRICE, QUANTITY,
        DESCRIPTION, options);
    productBottomSheetFragment = new ProductBottomSheetFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable("productDetails", Parcels.wrap(productDetails));
    productBottomSheetFragment.setArguments(bundle);
    startFragment(productBottomSheetFragment);
  }

  @Test
  public void testProductBottomSheetFragmentNotNull() {
    assertNotNull(productBottomSheetFragment);
  }

  @Test
  public void testQuantity(){
    productBottomSheetFragment.addQuantity();
    productBottomSheetFragment.removeQuantity();
  }

  @Resetter
  public void reset(){
    Robolectric.reset();
  }

}
