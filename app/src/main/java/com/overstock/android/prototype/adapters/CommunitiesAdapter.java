package com.overstock.android.prototype.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.models.Community;

import java.util.Collections;
import java.util.List;

/**
 * Created by rconnolly on 2/29/2016.
 */
public class CommunitiesAdapter extends RecyclerView.Adapter<CommunitiesAdapter.CommunitiesViewHolder> {

  private LayoutInflater inflater;

  private List<Community> data = Collections.emptyList();

  public CommunitiesAdapter(Context context, List<Community> data) {

    this.data = data;
    this.inflater = inflater.from(context);
  }

  @Override
  public CommunitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = inflater.inflate(R.layout.activity_communities_card, parent, false);

    CommunitiesViewHolder holder = new CommunitiesViewHolder(view);

    return holder;
  }

  @Override
  public void onBindViewHolder(CommunitiesViewHolder holder, int position) {

    Community community = data.get(position);

    holder.communityImage.setImageResource(community.getImageId());
    holder.communityTitle.setText(community.getName());
  }

  @Override
  public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  class CommunitiesViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;

    ImageView communityImage;

    TextView communityTitle;

    public CommunitiesViewHolder(final View itemView) {
      super(itemView);

      cardView = (CardView) itemView.findViewById(R.id.cvCommunities);
      communityImage = (ImageView) itemView.findViewById(R.id.ivCommunities);
      communityTitle = (TextView) itemView.findViewById(R.id.tvCommunities);

      // itemView.setOnClickListener(new View.OnClickListener() {
      // @Override
      // public void onClick(View v) {
      //
      // Intent intent = new Intent(v.getContext(), FeedActivity.class);
      // v.getContext().startActivity(intent);
      //
      // //Toast.makeText(itemView.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
      // }
      // });
    }
  }
}
