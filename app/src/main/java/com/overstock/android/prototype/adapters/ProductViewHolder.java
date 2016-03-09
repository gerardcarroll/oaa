package com.overstock.android.prototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;

/**
 * @author LeeMeehan Created on 08-Mar-16.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder {

  @Bind(R.id.product_card_img)
  public ImageView imageView;

  public ProductViewHolder(View view) {
    super(view);
    ButterKnife.bind(this, view);
  }
}
