package com.overstock.android.prototype.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;

/**
 * @author LeeMeehan Created on 07-Apr-16.
 */
public class ProductBottomSheetFragment extends BottomSheetDialogFragment {

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

  private Float startingPrice;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.product_details_bottom_sheet, container, false);
    ButterKnife.bind(this, view);
    startingPrice = Float.parseFloat(totalAmount.getText().toString().substring(1));
    return view;
  }

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

  @OnClick(R.id.quantity_add)
  public void addQuantity() {
    String existingQuantity = txtIndicator.getText().toString();
    Integer newQuantity = Integer.parseInt(existingQuantity) + 1;
    txtIndicator.setText(newQuantity.toString());
    setFinalAmount(newQuantity);
  }

  @OnClick(R.id.quantity_remove)
  public void removeQuantity() {
    String existingQuantity = txtIndicator.getText().toString();
    if (Integer.parseInt(existingQuantity) > 1) {
      Integer newQuantity = Integer.parseInt(existingQuantity) - 1;
      txtIndicator.setText(newQuantity.toString());
      setFinalAmount(newQuantity);
    }
  }

  private void setFinalAmount(Integer quantity) {
    Float newPrice = startingPrice * quantity;
    totalAmount.setText(String.format("$%.2f", newPrice));
  }

  @OnClick(R.id.rewards_btn_apply)
  public void applyDiscount() {
    String totalPrice = totalAmount.getText().toString().substring(1);
    String discount = rewardsAmount.getText().toString().substring(1);
    rewardsAmount.setText("$0.00");
    Float finalPrice = (Float.parseFloat(totalPrice) - Float.parseFloat(discount));
    totalAmount.setText(String.format("$%.2f", finalPrice));
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
      ((BottomSheetBehavior) behavior).setPeekHeight(600);
      ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetCallback);
    }
  }

}
