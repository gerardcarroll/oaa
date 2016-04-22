package com.overstock.android.prototype.presenter.impl;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedPagerAdapter;
import com.overstock.android.prototype.fragment.FeedFragment;
import com.overstock.android.prototype.fragment.MyLocationFragment;
import com.overstock.android.prototype.fragment.TrendingFragment;
import com.overstock.android.prototype.presenter.FeedActivityPresenter;
import com.overstock.android.prototype.ui.adapter.NavDrawerRecyclerViewAdapter;
import com.overstock.android.prototype.ui.model.NavDrawerModel;
import com.overstock.android.prototype.ui.service.JsonFileLoader;
import com.overstock.android.prototype.view.FeedActivityView;

/**
 * Created by itowey on 21/04/16.
 */
public class FeedActivityPresenterImpl implements FeedActivityPresenter {

    FeedActivityView feedActivityView;

    @Override
    public void attachView(FeedActivityView feedActivityView) {
        this.feedActivityView = feedActivityView;
    }

    @Override
    public void detachView() {
        this.feedActivityView = null;
    }

    @Override
    public void addDrawerItems() {
        if (feedActivityView.getNavDrawerModel() == null) {
            feedActivityView.setNavDrawerModel(new NavDrawerModel(feedActivityView.getContext(), new JsonFileLoader()));
        }

        feedActivityView.setmAdapter(new NavDrawerRecyclerViewAdapter(feedActivityView.getContext(), feedActivityView.getNavDrawerModel().getNavDrawerItems()));
        feedActivityView.getmRecyclerView().setAdapter(feedActivityView.getmAdapter());
    }

    @Override
    public void setupDrawer() {
        feedActivityView.setmDrawerToggle(new ActionBarDrawerToggle(
                feedActivityView.getAppCompatActivity(),
                feedActivityView.getmDrawerLayout(),
                R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                feedActivityView.getAppCompatActivity().invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                feedActivityView.getAppCompatActivity().invalidateOptionsMenu();
            }
        });

        feedActivityView.getmDrawerToggle().setDrawerIndicatorEnabled(true);
        feedActivityView.getmDrawerLayout().addDrawerListener(feedActivityView.getmDrawerToggle());
    }

    @Override
    public void setupViewPager(ViewPager viewPager) {
        FeedPagerAdapter adapter = feedActivityView.getFeedPagerAdapter();
        adapter.addFragment(new FeedFragment(), feedActivityView.getContext().getString(R.string.my_feed_tab));
        adapter.addFragment(new TrendingFragment(), feedActivityView.getContext().getString(R.string.trending_tab));
        adapter.addFragment(new MyLocationFragment(), feedActivityView.getContext().getString(R.string.my_location_tab));
        viewPager.setAdapter(adapter);
    }
}
