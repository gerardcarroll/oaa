package com.overstock.android.prototype.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedAdapter;
import com.overstock.android.prototype.client.FeedClient;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.models.Feed;
import com.overstock.android.prototype.service.FeedService;

import javax.inject.Inject;

public class FeedFragment extends Fragment {

  private static final String TAG = FeedFragment.class.getName();

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

    FeedClient.getClient(getContext()).getFeed().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<Feed>>() {
          @Override
          public void onCompleted() {
            Log.d(TAG, "ProductDataService.GetProduct has no more data to emit.");
          }

          @Override
          public void onError(Throwable e) {
            Log.e(TAG, "Error on subscribing to ProductDataService.GetProducts");
          }

          @Override
          public void onNext(List<Feed> feeds) {
            Log.d(TAG, "Next value on subscribing to ProductDataService.GetProducts");
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.stopNestedScroll();
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            final FeedAdapter feedCommunitiesAdapter = new FeedAdapter(feeds, getContext(), getActivity());
            recyclerView.setAdapter(feedCommunitiesAdapter);
          }
        });

    return rootView;
  }
}
