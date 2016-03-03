package com.overstock.android.prototype.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesAdapter extends RecyclerView.Adapter<CommunitiesAdapter.CommunitiesViewHolder> {

    private final Context context;
    private final int VIEW_CARDS = 1;
    private final int VIEW_PROG = 0;
    OnDataChangeListener mOnDataChangeListener;
    private LayoutInflater inflater;
    private List<Community> data;
    private final Animation anim;

    public CommunitiesAdapter(final Context context, final List<Community> data) {

        this.context = context;
        this.data = data;
        this.inflater = inflater.from(context);
        anim = AnimationUtils.loadAnimation(context, R.anim.scale);
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

      Picasso.with(context)
              .load(community.getImageId())
              .into(holder.communityImage, new Callback() {
                  @Override
                  public void onSuccess() {
                      if (holder.progressBar != null) {
                          holder.progressBar.setVisibility(View.GONE);
                      }
                  }

                  @Override
                  public void onError() {
                  }
              });

    holder.communityTitle.setText(community.getName());
    holder.chkSelected.setChecked(community.isSelected());
    holder.chkSelected.setTag(community);

    holder.chkSelected.setOnClickListener(new View.OnClickListener() {
      public void onClick(final View v) {
        final CheckBox cb = (CheckBox) v;
        final Community com = (Community) cb.getTag();

        com.setSelected(cb.isChecked());
        if (mOnDataChangeListener != null) {
          mOnDataChangeListener.onDataChanged(getSelectedCommunityList().size());
        }
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

    CardView cardView;

    ImageView communityImage;

    TextView communityTitle;

    CheckBox chkSelected;

    ProgressBar progressBar;

    public CommunitiesViewHolder(final View itemView) {
        super(itemView);

        cardView = (CardView) itemView.findViewById(R.id.cvCommunities);
        communityImage = (ImageView) itemView.findViewById(R.id.ivCommunities);
        communityTitle = (TextView) itemView.findViewById(R.id.tvCommunities);
        chkSelected = (CheckBox) itemView.findViewById(R.id.communityCheckBox);
        progressBar = (ProgressBar) itemView.findViewById(R.id.pbCommunities);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final CardView cv = (CardView) v;
                final RelativeLayout relLayout = (RelativeLayout) cv.getChildAt(0);
                final Community com = (Community) relLayout.getChildAt(3).getTag();
                if (com.isSelected()) {
                    com.setSelected(false);
                    chkSelected.setChecked(false);
                } else {
                    com.setSelected(true);
                    chkSelected.setChecked(true);
                }

                if (mOnDataChangeListener != null) {
                    mOnDataChangeListener.onDataChanged(getSelectedCommunityList().size());
                }
            }
        });

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //v.startAnimation(anim);

                return false;
            }
        });
    }
  }
}
