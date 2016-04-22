package com.overstock.android.prototype.presenter;

import android.support.v4.view.ViewPager;

import com.overstock.android.prototype.view.FeedActivityView;

/**
 * Created by itowey on 21/04/16.
 */
public interface FeedActivityPresenter {

    void attachView(FeedActivityView feedActivityView);
    void detachView();

    void addDrawerItems();

    void setupDrawer();

    void setupViewPager(ViewPager viewPager);
}
