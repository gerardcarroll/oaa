package com.overstock.android.prototype.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.overstock.android.prototype.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author RayConnolly Created on 4/5/2016.
 */
public class CommunityViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.cvCommunities)
    CardView cardView;

    @Bind(R.id.ivCommunities)
    ImageView communityImage;

    @Bind(R.id.tvCommunities)
    TextView communityTitle;

    @Bind(R.id.pbCommunities)
    ProgressBar progressBar;

    public CommunityViewHolder(final View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
