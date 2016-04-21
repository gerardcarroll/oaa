package com.overstock.android.prototype.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedAdapter;
import com.overstock.android.prototype.component.FeedActivityComponent;
import com.overstock.android.prototype.model.Feed;
import com.overstock.android.prototype.presenter.FeedPresenter;
import com.overstock.android.prototype.view.FeedView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author RayConnolly, LeeMeehan
 */
public class FeedFragment extends Fragment implements FeedView {
  private static final String TAG = FeedFragment.class.getName();

  @Inject
  FeedPresenter feedPresenter;

  @Bind(R.id.rv_feed)
  RecyclerView recyclerView;

  @Bind(R.id.feed_progressLoader)
  ProgressBar progressBar;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FeedActivityComponent.Initializer.init(this.getContext()).inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_feed, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    feedPresenter.setView(this);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
    feedPresenter.onDestroy();
  }

  @Override
  public void updateFeed(final ArrayList<Feed> feed) {
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.stopNestedScroll();
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    Log.d(TAG, "Setting initial feed");
    final FeedAdapter feedCommunitiesAdapter = new FeedAdapter(feed, getContext(), getActivity());
    recyclerView.setAdapter(feedCommunitiesAdapter);
    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
    animation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {
        Animation animationFade = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        progressBar.startAnimation(animationFade);
        progressBar.setVisibility(View.GONE);
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
    progressBar.startAnimation(animation);
  }

}
