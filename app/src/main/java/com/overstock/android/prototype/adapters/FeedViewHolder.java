package com.overstock.android.prototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.overstock.android.prototype.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author LeeMeehan Created on 31-Mar-16.
 */
public class FeedViewHolder extends RecyclerView.ViewHolder {

  public View view;

  @Bind(R.id.feed_img)
  public ImageView feedImg;

  @Bind(R.id.product_link)
  public TextView productLinkTxt;

  @Bind(R.id.product_website_link)
  public TextView tvWebsiteLinkTxt;

  public FeedViewHolder(View view) {
    super(view);
    this.view = view;
    ButterKnife.bind(this, view);
  }
}
