package com.overstock.android.prototype.fragment;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import javax.inject.Inject;

import org.parceler.Parcels;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.OptionAdapter;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.ProductDetail;
import com.overstock.android.prototype.presenter.ProductBottomSheetPresenter;
import com.overstock.android.prototype.utils.ViewAnimationUtils;
import com.overstock.android.prototype.view.ProductBottomSheetView;

/**
 * This class is used to display the BottomSheetDialogFragment. It also handles interactions between the UI on the
 * ProductBottomSheet. It is responsible for displaying UI only, all logical processes are in the presenter.
 *
 * @author LeeMeehan
 * @since Created on 07-Apr-16.
 * @see android.support.design.widget.BottomSheetDialogFragment
 */
public class ProductBottomSheetFragment extends BottomSheetDialogFragment implements ProductBottomSheetView {

  private static final String TAG = ProductBottomSheetFragment.class.getName();

  private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
    @Override
    public void onStateChanged(@NonNull View bottomSheet, int newState) {
      if (newState == BottomSheetBehavior.STATE_HIDDEN) {
        dismiss();
      }
    }

    @Override
    public void onSlide(@NonNull View bottomSheet, float slideOffset) {

    }
  };

  @Inject
  ProductBottomSheetPresenter presenter;

  @Bind(R.id.quantity_add)
  ImageView imageViewAdd;

  @Bind(R.id.quantity_remove)
  ImageView imageViewRemove;

  @Bind(R.id.quantity_indicator)
  TextView txtIndicator;

  @Bind(R.id.rewards_amount_txt)
  TextView rewardsAmount;

  @Bind(R.id.totalPrice_txt)
  TextView totalAmount;

  @Bind(R.id.options_spinner)
  Spinner spinner;

  @Bind(R.id.product_options_txt)
  TextView productOptionsTxt;

  @Bind(R.id.product_options_sheet)
  LinearLayout productOptionsSheet;

  @Bind(R.id.purchase_information)
  GridLayout purchaseInformationLayout;

  @Bind(R.id.layout_paymentMethod)
  LinearLayout paymentLayout;

  @Bind(R.id.layout_rewards)
  LinearLayout rewardsLayout;

  @Bind(R.id.order_information)
  GridLayout orderInformationLayout;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.product_details_bottom_sheet, container, false);
    ((OAppPrototypeApplication) getActivity().getApplication()).getComponent().inject(this);
    ButterKnife.bind(this, view);
    final ProductDetail productDetail = Parcels.unwrap(getArguments().getParcelable("productDetails"));
    Log.i(TAG, String.format("Product Id: %s", productDetail.getId()));
    presenter.setView(this);

    final String placeholderRewards = "12.50";
    presenter.setRewardsApplied(Float.valueOf(placeholderRewards));

    presenter.updateProductPage(productDetail);
    return view;
  }

  @OnClick(R.id.quantity_add)
  public void addQuantity() {
    final String existingQuantity = txtIndicator.getText().toString();
    presenter.addQuantity(Integer.parseInt(existingQuantity));
  }

  @Override
  public void displayToast(final String message) {
    final Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 20);
    toast.show();
  }

  @OnClick(R.id.quantity_remove)
  public void removeQuantity() {
    final String existingQuantity = txtIndicator.getText().toString();
    presenter.removeQuantity(Integer.parseInt(existingQuantity));
  }

  @OnClick(R.id.rewards_btn_apply)
  public void applyDiscount() {
    try {
      String totalPrice = totalAmount.getText().toString().substring(1);
      String discountApplied = rewardsAmount.getText().toString().substring(1);
      presenter.applyDiscount(totalPrice, discountApplied);
    }
    catch (Exception ex){
      Log.e(TAG,"Catching exception due to placeholder code." + ex.toString());
    }
  }

  @OnClick(R.id.btn_android_pay)
  public void payWithGoogleWallet() {
    final Toast toast = Toast.makeText(getActivity(), "Android Pay coming soon!", Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 20);
    toast.show();
    dismiss();
  }

  @OnClick(R.id.btn_submit_order)
  public void payWithCreditCard() {
    final Toast toast = Toast.makeText(getActivity(), "Checkout coming soon!", Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 20);
    toast.show();
    dismiss();
  }

  @Override
  public void setupDialog(Dialog dialog, int style) {
    super.setupDialog(dialog, style);
    View contentView = View.inflate(getContext(), R.layout.product_details_bottom_sheet, null);
    dialog.setContentView(contentView);

    CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent())
        .getLayoutParams();
    CoordinatorLayout.Behavior behavior = params.getBehavior();

    if (behavior != null && behavior instanceof BottomSheetBehavior) {
      ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetCallback);
      ((BottomSheetBehavior) behavior).setPeekHeight(getOptimizedPeekHeight());
    }
  }

  private int getOptimizedPeekHeight() {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics.heightPixels;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    presenter.onDestroy();
  }

  @Override
  public void updateQuantityIndicator(int newQuantity) {
    txtIndicator.setText(String.valueOf(newQuantity));
  }

  @Override
  public void updateFinalPrice(final float finalPrice) {
    final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    totalAmount.setText(getContext().getString(R.string.product_price_fmt_v2, currencyCode, finalPrice));
  }

  @Override
  public void updateRewardsAmount(final float rewardAmount) {
    final String currencyCode = Currency.getInstance(Locale.US).getSymbol();
    rewardsAmount.setText(getContext().getString(R.string.product_price_fmt_v2, currencyCode, rewardAmount));
  }

  @Override
  public void toggleSpinner() {
    if (spinner.getVisibility() == View.VISIBLE) {
      productOptionsTxt.setVisibility(View.GONE);
      spinner.setVisibility(View.GONE);
    }
    else {
      productOptionsTxt.setVisibility(View.VISIBLE);
      spinner.setVisibility(View.VISIBLE);
    }
  }

  /* Private method to setup and display the spinner. */
  @Override
  public void updateSpinner(final ArrayList<Options> options) {
    final Options placeholderOption = new Options(0, getResources().getString(R.string.option_placeholder_test), 0, 0,
        0);
    options.add(0, placeholderOption);
    final OptionAdapter optionAdapter = new OptionAdapter(options, getActivity());
    spinner.setAdapter(optionAdapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
          final Options option = (Options) optionAdapter.getItem(position);
          presenter.setMaxQuantityAllowed(option.getMaxQuantityAllowed());
          presenter.setCurrentPrice(option.getPrice());
          final Integer existingQuantity = Integer.parseInt(txtIndicator.getText().toString());
          if (existingQuantity < option.getMaxQuantityAllowed()) {
            presenter.updateFinalPrice(existingQuantity);
          }
          else {
            updateQuantityIndicator(1);
            handleQuantityIcons(1, option.getMaxQuantityAllowed());
          }
          presenter.resetRewards();
          Log.i(TAG, String.valueOf(option.getMaxQuantityAllowed()));
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  @OnClick(R.id.add_option_btn)
  public void showOptionSheet() {
    if (productOptionsSheet.getVisibility() == View.GONE) {
      ViewAnimationUtils.expand(productOptionsSheet);
      collapseOrderInformationContent();
    }
  }

  @OnClick(R.id.btn_product_options_btn)
  public void hideOptionSheet() {
    if (spinner.getSelectedItemPosition() > 0) {
      Options selectedOption = (Options) spinner.getSelectedItem();
      Log.i(TAG, selectedOption.getDescription());
      presenter.initiateCheckout();
    }
    if (productOptionsSheet.getVisibility() == View.VISIBLE) {
      ViewAnimationUtils.collapse(productOptionsSheet);
      expandOrderInformationContent();
    }
  }

  /* Method that handel's collapsing all unnecessary ui views. */
  private void collapseOrderInformationContent() {
    ViewAnimationUtils.collapse(rewardsLayout);
    ViewAnimationUtils.collapse(paymentLayout);
    ViewAnimationUtils.collapse(purchaseInformationLayout);
    ViewAnimationUtils.collapse(orderInformationLayout);
  }

  /* Method that handel's collapsing all necessary ui views. */
  private void expandOrderInformationContent() {
    ViewAnimationUtils.expand(rewardsLayout);
    ViewAnimationUtils.expand(paymentLayout);
    ViewAnimationUtils.expand(purchaseInformationLayout);
    ViewAnimationUtils.expand(orderInformationLayout);
  }

  /* Method for handling UI disabling and enabling of quantity buttons. */
  @Override
  public void handleQuantityIcons(final int currentQuantity, final int maxQuantityAllowed) {
    if (currentQuantity == maxQuantityAllowed) {
      imageViewAdd.setImageResource(R.drawable.ic_add_circle_disabled_24dp);
      imageViewRemove.setImageResource(R.drawable.ic_remove_circle_24dp);
    }
    else if (currentQuantity == 1) {
      imageViewRemove.setImageResource(R.drawable.ic_remove_circle_disabled_24dp);
      imageViewAdd.setImageResource(R.drawable.ic_add_circle_24dp);
    }
    else {
      imageViewAdd.setImageResource(R.drawable.ic_add_circle_24dp);
      imageViewRemove.setImageResource(R.drawable.ic_remove_circle_24dp);
    }
  }
}
