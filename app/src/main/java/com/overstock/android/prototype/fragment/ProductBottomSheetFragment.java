package com.overstock.android.prototype.fragment;

import java.util.ArrayList;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.OptionAdapter;
import com.overstock.android.prototype.model.Options;

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

  @Bind(R.id.options_spinner)
  Spinner spinner;

  private Float startingPrice;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.product_details_bottom_sheet, container, false);
    ButterKnife.bind(this, view);
    startingPrice = Float.parseFloat(totalAmount.getText().toString().substring(1));
    // TODO replace with real data.
    ArrayList<Options> options = new ArrayList<>();
    options.add(new Options(2, "Hey", 22, 22, new Float(2.2)));
    options.add(new Options(2, "There", 22, 22, new Float(2.2)));
    options.add(new Options(2, "How", 22, 22, new Float(2.2)));
    options.add(new Options(2, "you", 22, 22, new Float(2.2)));
    OptionAdapter optionAdapter = new OptionAdapter(options, getActivity());
    spinner.setAdapter(optionAdapter);
    // TODO Convert to mvp
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
    // TODO Replace placeholder logic
    String existingQuantity = txtIndicator.getText().toString();
    Integer newQuantity = Integer.parseInt(existingQuantity) + 1;
    txtIndicator.setText(newQuantity.toString());
    setFinalAmount(newQuantity);
  }

  @OnClick(R.id.quantity_remove)
  public void removeQuantity() {
    // TODO Replace placeholder logic
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
    // TODO Replace placeholder logic
    String totalPrice = totalAmount.getText().toString().substring(1);
    String discount = rewardsAmount.getText().toString().substring(1);
    rewardsAmount.setText("$0.00");
    Float finalPrice = (Float.parseFloat(totalPrice) - Float.parseFloat(discount));
    startingPrice = finalPrice;
    totalAmount.setText(String.format("$%.2f", finalPrice));
  }

  @OnClick(R.id.btn_pay_google_wallet)
  public void payWithGoogleWallet() {
    final Toast toast = Toast.makeText(getActivity(), "Google Wallet Payment coming soon!", Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.BOTTOM, 0, 20);
    toast.show();
    dismiss();
  }

  @OnClick(R.id.btn_pay_credit_card)
  public void payWithCreditCard() {
    final Toast toast = Toast.makeText(getActivity(), "Credit Card Payment coming soon!", Toast.LENGTH_SHORT);
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
      ((BottomSheetBehavior) behavior).setPeekHeight(600);
      ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetCallback);
    }
  }

}
