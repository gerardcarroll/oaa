package com.overstock.android.prototype.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesAdapter extends RecyclerView.Adapter<CommunitiesAdapter.CommunitiesViewHolder> {

    private final int VIEW_CARDS = 1;
    private final int VIEW_PROG = 0;
    OnDataChangeListener mOnDataChangeListener;
    private LayoutInflater inflater;
    private List<Community> data;

    public CommunitiesAdapter(final Context context, final List<Community> data) {
        this.data = data;
        this.inflater = inflater.from(context);
    }

    @Override
    public int getItemViewType(final int position) {
        return data.get(position) != null ? VIEW_CARDS : VIEW_PROG;
    }

    @Override
    public CommunitiesViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        if (viewType == VIEW_CARDS) {
            final View view = inflater.inflate(R.layout.activity_communities_card, parent, false);
            return new CommunitiesViewHolder(view);
        } else {
            final View view = inflater.inflate(R.layout.activity_communities_card, parent, false);
            return new CommunitiesViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final CommunitiesViewHolder holder, final int position) {

        final Community community = data.get(position);

        holder.communityImage.setImageResource(community.getImageId());
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

        public CommunitiesViewHolder(final View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cvCommunities);
            communityImage = (ImageView) itemView.findViewById(R.id.ivCommunities);
            communityTitle = (TextView) itemView.findViewById(R.id.tvCommunities);
            chkSelected = (CheckBox) itemView.findViewById(R.id.communityCheckBox);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final CardView cv = (CardView) v;
                    final RelativeLayout relLayout = (RelativeLayout) cv.getChildAt(0);
                    final Community com = (Community) relLayout.getChildAt(2).getTag();
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
        }
    }
}
