package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedPagerAdapter;
import com.overstock.android.prototype.fragment.ArcMenuFragment;
import com.overstock.android.prototype.fragment.FeedFragment;
import com.overstock.android.prototype.fragment.MyLocationFragment;
import com.overstock.android.prototype.fragment.TrendingFragment;
import com.overstock.android.prototype.ui.adapter.NavDrawerRecyclerViewAdapter;
import com.overstock.android.prototype.ui.model.NavDrawerModel;
import com.overstock.android.prototype.ui.service.JsonFileLoader;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

public class FeedActivity extends AppCompatActivity {

  @Bind(R.id.product_detail_toolbar)
  Toolbar toolbar;

  @Bind(R.id.feed_viewpager)
  ViewPager viewPager;

  @Bind(R.id.feed_tabs)
  TabLayout tabLayout;

  private ArcMenuFragment fragment = null;

  private android.support.v4.app.FragmentManager manager = null;

  private android.support.v4.app.FragmentTransaction ft;

  @Bind(R.id.drawer_layout)
  DrawerLayout mDrawerLayout;

  @Bind(R.id.navRecyclerView)
  RecyclerView mRecyclerView;

  @State(NavDrawerModelBundler.class)
  NavDrawerModel navDrawerModel;

  private ActionBarDrawerToggle mDrawerToggle;

  private RecyclerView.Adapter mAdapter;

  private RecyclerView.LayoutManager mLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);

    ButterKnife.bind(this);

    setupViewPager(viewPager);
    tabLayout.setupWithViewPager(viewPager);

    // Add Arc Menu fragment
    if (manager == null)
      manager = getSupportFragmentManager();
    if (manager.findFragmentById(R.id.arc_menu_fragment_container) == null) {
      fragment = new ArcMenuFragment();
      ft = manager.beginTransaction();
      ft.add(R.id.arc_menu_fragment_container, fragment).commit();
    }

    mRecyclerView.setHasFixedSize(true); // Letting the system know that the list objects are of fixed size
    mLayoutManager = new LinearLayoutManager(this);
    mRecyclerView.setLayoutManager(mLayoutManager);
    addDrawerItems();

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setTitle("");
    setupDrawer();
    mDrawerToggle.syncState();
  }

  private void setupViewPager(ViewPager viewPager) {
    FeedPagerAdapter adapter = new FeedPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new FeedFragment(), getString(R.string.my_feed_tab));
    adapter.addFragment(new TrendingFragment(), getString(R.string.trending_tab));
    adapter.addFragment(new MyLocationFragment(), getString(R.string.my_location_tab));
    viewPager.setAdapter(adapter);
  }

  @Override
  public boolean onCreateOptionsMenu(final Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(final MenuItem item) {

    final int id = item.getItemId();

    if (id == R.id.action_settings || id == R.id.action_refresh || id == R.id.action_logout) {
      Toast.makeText(this, "You clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
      return true;
    }

    if (mDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void addDrawerItems() {
    if (navDrawerModel == null) {
      navDrawerModel = new NavDrawerModel(this, new JsonFileLoader());
    }

    mAdapter = new NavDrawerRecyclerViewAdapter(this, navDrawerModel.getNavDrawerItems());
    mRecyclerView.setAdapter(mAdapter);
  }

  private void setupDrawer() {
    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        invalidateOptionsMenu();
      }

      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        invalidateOptionsMenu();
      }
    };

    mDrawerToggle.setDrawerIndicatorEnabled(true);
    mDrawerLayout.addDrawerListener(mDrawerToggle);
  }

}
