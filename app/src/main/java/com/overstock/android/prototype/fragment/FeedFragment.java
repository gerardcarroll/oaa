package com.overstock.android.prototype.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedAdapter;
import com.overstock.android.prototype.models.Feed;

public class FeedFragment extends Fragment {

  @Bind(R.id.rv_feed_communities)
  RecyclerView recyclerView;

  public FeedFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
    ButterKnife.bind(this, rootView);
    final ArrayList<Feed> feeds = new ArrayList<>();

    // TODO only using this for now to populate recycler view
    // TODO set up feed specific data and implement using presenter class
    final String[] imageReferenceArray = getContext().getResources().getStringArray(R.array.feed_image_array);

    final int len = imageReferenceArray.length;
    final int[] imagesArray = new int[len];
    for (int i = 0; i < len; i++) {
      imagesArray[i] = getContext().getResources().getIdentifier(imageReferenceArray[i], "drawable",
        getContext().getPackageName());
    }

    feeds.add(new Feed(imagesArray[0], "Top NFL Fan Products for 2016", "NFL.com"));
    feeds.add(new Feed(imagesArray[1], "Get Your Beat on!, with Beats by dre", "DreBeats.com"));
    feeds.add(new Feed(imagesArray[2], "Treat yourself. Top skin care products of this month.", "Relax.com"));
    feeds.add(new Feed(imagesArray[3], "Must Have Products to get in Shape.", "GetFit.com"));
    // TODO remove up as far as last todo

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.stopNestedScroll();
    recyclerView.setItemAnimator(new DefaultItemAnimator());

    final FeedAdapter feedCommunitiesAdapter = new FeedAdapter(feeds, getContext(), getActivity());
    recyclerView.setAdapter(feedCommunitiesAdapter);

    return rootView;
  }
}
