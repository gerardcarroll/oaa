package com.overstock.android.prototype.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.models.Feed;
import com.vstechlab.easyfonts.EasyFonts;

/**
 * Created by RayConnolly on 3/16/2016.
 */
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

  private Context context;

  private Activity activity;

  private List<Feed> feedItems = new ArrayList<>();

  public FeedAdapter(final List<Feed> items, final Context context, final Activity activity) {
    this.activity = activity;
    this.context = context;
    this.feedItems = items;
  }

  public List<Feed> getData() {
    return feedItems;
  }

  public void setData(List<Feed> feed) {
    this.feedItems = feed;
  }

  @Override
  public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feed_card, parent, false);

    ViewHolder viewHolder = new ViewHolder(itemLayoutView);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(FeedAdapter.ViewHolder holder, int position) {

    Feed feed = feedItems.get(position);

    holder.ivCommunity.setImageResource(feed.getProductImage());
    holder.tvViewCommunity.setText(feed.getTopProductsLink());
    holder.tvViewCommunity.setTypeface(EasyFonts.robotoBlack(context));
    holder.tvProductLink.setText(feed.getProductUrl());

    holder.ivCommunity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(context,
          R.transition.slide_in_horizontal, R.transition.slide_out_horizontal);

        context.startActivity(new Intent(activity, BrandActivity.class), options.toBundle());
      }
    });
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    View view;

    @Bind(R.id.feed_img)
    public ImageView ivCommunity;

    @Bind(R.id.product_link)
    public TextView tvViewCommunity;

    @Bind(R.id.product_website_link)
    public TextView tvProductLink;

    public ViewHolder(View itemLayoutView) {
      super(itemLayoutView);

      this.view = itemLayoutView;

      ButterKnife.bind(this, itemLayoutView);
    }
  }

  @Override
  public int getItemCount() {
    return feedItems.size();
  }
}
