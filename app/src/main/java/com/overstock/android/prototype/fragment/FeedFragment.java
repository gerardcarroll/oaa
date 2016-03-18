package com.overstock.android.prototype.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.res.TypedArray;
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
import com.overstock.android.prototype.adapters.CustomFeedAdapter;
import com.overstock.android.prototype.models.Feed;

public class FeedFragment extends Fragment {

    private CustomFeedAdapter feedCommunitiesAdapter;

    @Bind(R.id.rv_feed_communities)
    RecyclerView recyclerView;

    public FeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);

        ButterKnife.bind(this, rootView);

        ArrayList<Feed> feeds = new ArrayList<>();

        // TODO only using this for now to populate recycler view
        // TODO set up feed specific data and implement using presenter class
        final TypedArray typedArray = getContext().getResources().obtainTypedArray(R.array.feed_image_array);

        final int len = typedArray.length();
        final int[] imagesArray = new int[len];
        for (int i = 0; i < len; i++) {
            imagesArray[i] = typedArray.getResourceId(i, 0);
        }

        final String[] names = getContext().getResources().getStringArray(R.array.feed_string_array);

        feeds.add(new Feed(imagesArray[0],"Top NFL Fan Products for 2016","NFL.com"));
        feeds.add(new Feed(imagesArray[1],"Sleep like a God with These Beds","BestBeds.com"));
        feeds.add(new Feed(imagesArray[2],"Must Have Products to get in Shape","fitness.com"));
        // TODO remove up as far as last todo

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.stopNestedScroll();
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            feedCommunitiesAdapter = new CustomFeedAdapter(feeds,getContext(),getActivity());
            recyclerView.setAdapter(feedCommunitiesAdapter);

            return rootView;
        }

//
//    @OnClick(R.id.feed_communities_btn)
//    public void onCommunitiesBtnClick() {
//        final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getContext(),
//                R.transition.slide_in_vertical, R.transition.slide_out_horizontal);
//        startActivity(new Intent(getActivity(), CommunitiesActivity.class), options.toBundle());
//    }
}
