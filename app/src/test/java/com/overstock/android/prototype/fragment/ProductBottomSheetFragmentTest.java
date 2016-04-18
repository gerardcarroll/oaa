package com.overstock.android.prototype.fragment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.parceler.Parcels;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import android.os.Build;
import android.os.Bundle;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.presenter.ProductBottomSheetPresenter;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * @author LeeMeehan.
 * @since Created on 13-Apr-16
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class ProductBottomSheetFragmentTest {

  @Rule
  public final DaggerMockRule<ApplicationComponent> mockRule = new DaggerMockRule<>(ApplicationComponent.class,
      new ApplicationModule(new OAppPrototypeApplication()))
          .set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
            @Override
            public void setComponent(ApplicationComponent applicationComponent) {
              ((OAppPrototypeApplication) RuntimeEnvironment.application).setComponent(applicationComponent);
            }
          });

  private static final Integer PRODUCT_DETAIL_ID = 1001;

  private static final Integer OPTION_ID = 2002;

  private static final String PRODUCT_NAME = "TEST_NAME";

  private static final String IMAGE_LARGE = "TEST_IMAGE_LARGE";

  private static final String IMAGE_MEDIUM = "TEST_IMAGE_MEDIUM";

  private static final Float PRICE = Float.valueOf("22.50");

  private static final Float OPTION_PRICE = Float.valueOf("25.50");

  private static final Integer QUANTITY = 5;

  private static final Integer OPTION_QUANTITY_ALLOWED = 7;

  private static final Integer OPTION_QUANTITY_ON_HAND = 10;

  private static final String DESCRIPTION = "TEST_DESCRIPTION";

  private static final String OPTION_DESCRIPTION = "TEST_OPTION_DESCRIPTION";

  private ProductBottomSheetFragment productBottomSheetFragment;

  private ProductDetail productDetails;

  private ArrayList<Options> options;

  @Mock
  private ProductBottomSheetPresenter productBottomSheetPresenter;

  @Before
  public void setUp() {
    productBottomSheetFragment = new ProductBottomSheetFragment();
    options = new ArrayList<>();
    final Options option = new Options(OPTION_ID, OPTION_DESCRIPTION, OPTION_QUANTITY_ON_HAND, OPTION_QUANTITY_ALLOWED,
        OPTION_PRICE);
    options.add(option);
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
    verify(productBottomSheetPresenter).setView(productBottomSheetFragment);
    verify(productBottomSheetPresenter).updateProductPage(productDetails);
  }

  @Test
  public void testQuantity() {
    productBottomSheetFragment.addQuantity();
    verify(productBottomSheetPresenter).addQuantity(any(Integer.class));
    productBottomSheetFragment.removeQuantity();
    verify(productBottomSheetPresenter).removeQuantity(any(Integer.class));
  }

  @Test
  public void testDisplayToast() {
    final String testMessage = "HI THERE.";
    productBottomSheetFragment.displayToast(testMessage);
    assertEquals(testMessage, ShadowToast.getTextOfLatestToast());
  }

  @Test
  public void updateSpinner() {
    productBottomSheetFragment.updateSpinner(options);
  }

}
