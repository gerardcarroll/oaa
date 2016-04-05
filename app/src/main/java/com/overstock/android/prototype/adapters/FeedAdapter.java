package com.overstock.android.prototype.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Feed;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

/**
 * @author RayConnolly, LeeMeehan Created on 3/16/2016.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

  @Inject
  Picasso picasso;

  private Context context;

  private Activity activity;

  private List<Feed> feedItems = new ArrayList<>();

  public FeedAdapter(final List<Feed> items, final Context context, final Activity activity) {
    this.activity = activity;
    this.context = context;
    this.feedItems = items;
    ((OAppPrototypeApplication) context.getApplicationContext()).getComponent().inject(this);
  }

  public List<Feed> getData() {
    return feedItems;
  }

  public void setData(List<Feed> feed) {
    this.feedItems = feed;
  }

  @Override
  public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feed_card, parent, false);
    return new FeedViewHolder(itemLayoutView);
  }

  @Override
  public void onBindViewHolder(FeedViewHolder holder, int position) {

    final Feed feed = feedItems.get(position);
    picasso.load(feed.getProductImage()).error(R.drawable.product_placeholder).into(holder.feedImg);

    holder.productLinkTxt.setText(feed.getTopProductsLink());
    holder.productLinkTxt.setTypeface(EasyFonts.robotoBlack(context));
    holder.tvWebsiteLinkTxt.setText(feed.getProductUrl());

    holder.feedImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(context,
          R.transition.slide_in_horizontal, R.transition.slide_out_horizontal);

        context.startActivity(new Intent(activity, BrandActivity.class), options.toBundle());
      }
    });
    animate(holder.view, position);
  }

  private void animate(final View view, final int position) {
    view.animate().cancel();
    view.setTranslationY(100);
    view.setAlpha(0);
    view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(position * 100);
  }

  @Override
  public int getItemCount() {
    return feedItems.size();
  }
}
