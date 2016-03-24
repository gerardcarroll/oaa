package com.overstock.android.prototype.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * @author RayConnolly Created on 2/29/2016.
 */
public class CommunitiesAdapter extends RecyclerView.Adapter<CommunitiesAdapter.CommunitiesViewHolder> {

  private final Context context;

  OnDataChangeListener mOnDataChangeListener;

  private LayoutInflater inflater;

  private List<Community> data = new ArrayList<>();

  public CommunitiesAdapter(final Context context) {

    this.context = context;
    this.inflater = inflater.from(context);
  }

  public List<Community> getData() {
    return data;
  }

  public void setData(List<Community> data) {
    this.data = data;
  }

  @Override
  public CommunitiesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
    final View view = inflater.inflate(R.layout.activity_communities_card, parent, false);
    return new CommunitiesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final CommunitiesViewHolder holder, final int position) {

    final Community community = data.get(position);

    holder.progressBar.setVisibility(View.VISIBLE);

    Picasso.with(context).load(community.getImageId()).resize(500, 500).into(holder.communityImage, new Callback() {
      @Override
      public void onSuccess() {
        if (holder.progressBar != null) {
          holder.progressBar.setVisibility(View.GONE);
        }
      }

      @Override
      public void onError() {}
    });

    holder.communityTitle.setText(community.getName());
    // If the Community isSelected (true) change color on title label
    if (!community.isSelected()) {
      holder.communityTitle.setBackgroundResource(R.drawable.rounded_corner_default);
    }
    else {
      holder.communityTitle.setBackgroundResource(R.drawable.rounded_corner_green);
    }
    holder.cardView.setTag(community);

    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(final View v) {
        final CardView cv = (CardView) v;
        final Community com = (Community) cv.getTag();
        if (com.isSelected()) {
          com.setSelected(false);
          holder.communityTitle.setBackgroundResource(R.drawable.rounded_corner_default);
          // holder.communityImage.clearColorFilter();
          holder.cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.continue_btn_bounce_revert));
        }
        else {
          com.setSelected(true);
          holder.communityTitle.setBackgroundResource(R.drawable.rounded_corner_green);
          // holder.communityImage.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
        }

        if (mOnDataChangeListener != null) {
          mOnDataChangeListener.onDataChanged(getSelectedCommunityList().size());
        }

        // Add Animation to view
        v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in));
      }
    });
  }

  @Override
  public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  private List<Community> getSelectedCommunityList() {
    final List<Community> selectedCommunities = new ArrayList<>();
    for (final Community community : data) {
      if (community.isSelected()) {
        selectedCommunities.add(community);
      }
    }
    return selectedCommunities;
  }

  public void setOnDataChangeListener(final OnDataChangeListener onDataChangeListener) {
    mOnDataChangeListener = onDataChangeListener;
  }

  public interface OnDataChangeListener {
    void onDataChanged(int size);
  }

  public class CommunitiesViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.cvCommunities)
    CardView cardView;

    @Bind(R.id.ivCommunities)
    ImageView communityImage;

    @Bind(R.id.tvCommunities)
    TextView communityTitle;

    @Bind(R.id.pbCommunities)
    ProgressBar progressBar;

    public CommunitiesViewHolder(final View itemView) {

      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
