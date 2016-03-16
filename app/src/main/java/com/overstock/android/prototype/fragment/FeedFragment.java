package com.overstock.android.prototype.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.activity.CommunitiesActivity;

public class FeedFragment extends Fragment {

  public FeedFragment() {}

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @OnClick(R.id.feed_nfl_btn)
  public void onNflBtnClick() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getContext(),
      R.transition.slide_in_horizontal, R.transition.slide_out_horizontal);
    startActivity(new Intent(getActivity(), BrandActivity.class), options.toBundle());
  }

  @OnClick(R.id.feed_communities_btn)
  public void onCommunitiesBtnClick() {
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(getContext(),
      R.transition.slide_in_vertical, R.transition.slide_out_horizontal);
    startActivity(new Intent(getActivity(), CommunitiesActivity.class), options.toBundle());
  }
}
