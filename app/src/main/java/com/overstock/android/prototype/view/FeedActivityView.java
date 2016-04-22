package com.overstock.android.prototype.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.overstock.android.prototype.adapters.FeedPagerAdapter;
import com.overstock.android.prototype.ui.model.NavDrawerModel;

/**
 * Created by itowey on 21/04/16.
 */
public interface FeedActivityView {

    void init();
    NavDrawerModel getNavDrawerModel();
    void setNavDrawerModel(NavDrawerModel navDrawerModel);
    Context getContext();
    RecyclerView.Adapter getmAdapter();

    void setmAdapter(RecyclerView.Adapter mAdapter);

    RecyclerView getmRecyclerView();

    ActionBarDrawerToggle getmDrawerToggle();

    void setmDrawerToggle(ActionBarDrawerToggle mDrawerToggle);

    DrawerLayout getmDrawerLayout();

    void setmDrawerLayout(DrawerLayout mDrawerLayout);

    FeedPagerAdapter getFeedPagerAdapter();

    AppCompatActivity getAppCompatActivity();
}
