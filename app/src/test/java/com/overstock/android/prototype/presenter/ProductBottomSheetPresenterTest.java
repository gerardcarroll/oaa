package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.impl.ProductBottomSheetPresenterImpl;
import com.overstock.android.prototype.view.ProductBottomSheetView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author LeeMeehan
 * @since Created on 14-Apr-16.
 */
public class ProductBottomSheetPresenterTest {

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

  private static final String REVIEW = "TEST_REVIEW";

  private static final String OPTION_DESCRIPTION = "TEST_OPTION_DESCRIPTION";

  private static final Integer MAX_QUANTITY_ALLOWED = 2;

  private static final Float CURRENT_PRICE = Float.valueOf("2.00");

  private ProductBottomSheetPresenter productBottomSheetPresenter;

  @Mock
  ProductBottomSheetView productBottomSheetView;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productBottomSheetPresenter = new ProductBottomSheetPresenterImpl();
    productBottomSheetPresenter.setView(productBottomSheetView);
    productBottomSheetPresenter.setCurrentPrice(CURRENT_PRICE);
  }

  @Test
  public void testAddQuantity() {
    final Integer QUANTITY_INPUT = 1;
    final Integer QUANTITY_OUTPUT = 2;
    productBottomSheetPresenter.setMaxQuantityAllowed(MAX_QUANTITY_ALLOWED);
    productBottomSheetPresenter.addQuantity(QUANTITY_INPUT);
    verify(productBottomSheetView).updateQuantityIndicator(QUANTITY_OUTPUT);
    final Float newPrice = Float.valueOf("4.00");
    verify(productBottomSheetView).updateFinalPrice(newPrice);
    verify(productBottomSheetView).handleQuantityIcons(QUANTITY_OUTPUT, MAX_QUANTITY_ALLOWED);
  }

  @Test
  public void testAddQuantity_MAX_REACHED() {
    final Integer QUANTITY_INPUT = 2;
    productBottomSheetPresenter.setMaxQuantityAllowed(MAX_QUANTITY_ALLOWED);
    productBottomSheetPresenter.addQuantity(QUANTITY_INPUT);
    verify(productBottomSheetView, never()).updateQuantityIndicator(anyInt());
    verify(productBottomSheetView, never()).updateFinalPrice(anyInt());
    verify(productBottomSheetView, never()).handleQuantityIcons(anyInt(), anyInt());
    verify(productBottomSheetView).displayToast(anyString());
  }

  @Test
  public void testRemovedQuantity() {
    final Integer QUANTITY_INPUT = 2;
    final Integer QUANTITY_OUTPUT = 1;
    productBottomSheetPresenter.setMaxQuantityAllowed(MAX_QUANTITY_ALLOWED);
    productBottomSheetPresenter.removeQuantity(QUANTITY_INPUT);
    verify(productBottomSheetView).updateQuantityIndicator(QUANTITY_OUTPUT);
    final Float newPrice = Float.valueOf("2.00");
    verify(productBottomSheetView).updateFinalPrice(newPrice);
    verify(productBottomSheetView).handleQuantityIcons(QUANTITY_OUTPUT, MAX_QUANTITY_ALLOWED);
  }

  @Test
  public void testRemovedQuantity_QUANTITY_ONE() {
    final Integer QUANTITY_INPUT = 1;
    productBottomSheetPresenter.setMaxQuantityAllowed(MAX_QUANTITY_ALLOWED);
    productBottomSheetPresenter.removeQuantity(QUANTITY_INPUT);
    verify(productBottomSheetView, never()).updateQuantityIndicator(anyInt());
    verify(productBottomSheetView, never()).updateFinalPrice(anyInt());
    verify(productBottomSheetView, never()).handleQuantityIcons(anyInt(), anyInt());
  }

  @Test
  public void TestUpdateProductPage() {
    List<Options> options = new ArrayList<>();
    final Options option = new Options(OPTION_ID, OPTION_DESCRIPTION, OPTION_QUANTITY_ON_HAND, OPTION_QUANTITY_ALLOWED,
        OPTION_PRICE);
    options.add(option);
    options.add(option);
    final ProductDetail productDetail = new ProductDetail(PRODUCT_DETAIL_ID, PRODUCT_NAME, IMAGE_LARGE, IMAGE_MEDIUM,
        PRICE, QUANTITY, DESCRIPTION, REVIEW, options, null);
    productBottomSheetPresenter.updateProductPage(productDetail);
    verify(productBottomSheetView).updateFinalPrice(productDetail.getMemberPrice());
    verify(productBottomSheetView).updateSpinner((ArrayList<Options>) options);
  }

  @Test
  public void TestUpdateProductPage_ONE_OPTION() {
    List<Options> options = new ArrayList<>();
    final Options option = new Options(OPTION_ID, OPTION_DESCRIPTION, OPTION_QUANTITY_ON_HAND, OPTION_QUANTITY_ALLOWED,
        OPTION_PRICE);
    options.add(option);
    final ProductDetail productDetail = new ProductDetail(PRODUCT_DETAIL_ID, PRODUCT_NAME, IMAGE_LARGE, IMAGE_MEDIUM,
        PRICE, QUANTITY, DESCRIPTION, REVIEW, options, null);
    productBottomSheetPresenter.updateProductPage(productDetail);
    verify(productBottomSheetView).updateFinalPrice(productDetail.getMemberPrice());
    verify(productBottomSheetView, never()).updateSpinner((ArrayList<Options>) options);
    verify(productBottomSheetView).toggleSpinner();
  }

  @Test
  public void testResetRewards() {
    final Float rewards_amount = Float.parseFloat("12.90");
    productBottomSheetPresenter.setRewardsApplied(rewards_amount);
    productBottomSheetPresenter.resetRewards();
    verify(productBottomSheetView).updateRewardsAmount(rewards_amount);
  }

  @Test
  public void testApplyDiscount() {
    final String totalPrice = "10.00";
    final String discount = "5.00";
    final Float remainingReward_amount = Float.valueOf("0.00");
    final Float price_output = Float.valueOf("5.00");
    productBottomSheetPresenter.applyDiscount(totalPrice, discount);
    verify(productBottomSheetView).updateRewardsAmount(remainingReward_amount);
    verify(productBottomSheetView).updateFinalPrice(price_output);
  }

  @Test(expected = NullPointerException.class)
  public void testApplyDiscount_VIEW_NULL() {
    final String totalPrice = "10.00";
    final String discount = "5.00";
    productBottomSheetPresenter.onDestroy();
    productBottomSheetPresenter.applyDiscount(totalPrice, discount);
  }

}
