package com.overstock.android.prototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Feed;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rconnolly on 3/16/2016.
 */
public class CustomFeedAdapter extends RecyclerView.Adapter<CustomFeedAdapter.ViewHolder>{

    private List<Feed> feedItems = new ArrayList<>();

    public CustomFeedAdapter(List<Feed> items){

        this.feedItems = items;
    }

    public List<Feed> getData() {
        return feedItems;
    }

    public void setData(List<Feed> feed) {
        this.feedItems = feed;
    }

    @Override
    public CustomFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feed_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomFeedAdapter.ViewHolder holder, int position) {

        Feed feed = feedItems.get(position);

        holder.ivCommunity.setImageResource(feed.getProductImage());
        holder.tvViewCommunity.setText(feed.getTopProductsLink());
        holder.tvProductLink.setText(feed.getProductUrl());
        holder.tvStats.setText(feed.getStats());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        int position;

        @Bind(R.id.feed_img)
        public ImageView ivCommunity;

        @Bind(R.id.product_link)
        public TextView tvViewCommunity;

        @Bind(R.id.product_website_link)
        public TextView tvProductLink;

        @Bind(R.id.product_stats)
        public TextView tvStats;


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
