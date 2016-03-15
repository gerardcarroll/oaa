package com.overstock.android.prototype.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.activity.CommunitiesActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedFragment extends Fragment {

    public FeedFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.feed_nfl_btn)
    public void onNflBtnClick(){
        Intent signInIntent = new Intent(getActivity(), BrandActivity.class);
        startActivity(signInIntent);
    }

    @OnClick(R.id.feed_communities_btn)
    public void onCommunitiesBtnClick(){
        Intent signInIntent = new Intent(getActivity(), CommunitiesActivity.class);
        startActivity(signInIntent);
    }
}
