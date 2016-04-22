package com.overstock.android.prototype.activity;

import android.content.Context;
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
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedPagerAdapter;
import com.overstock.android.prototype.component.FeedActivityComponent;
import com.overstock.android.prototype.fragment.ArcMenuFragment;
import com.overstock.android.prototype.presenter.FeedActivityPresenter;
import com.overstock.android.prototype.ui.model.NavDrawerModel;
import com.overstock.android.prototype.view.FeedActivityView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import icepick.State;

public class FeedActivity extends AppCompatActivity implements FeedActivityView {

  @Inject
  FeedActivityPresenter feedActivityPresenter;

  @Bind(R.id.product_detail_toolbar)
  Toolbar toolbar;

  @Bind(R.id.feed_viewpager)
  ViewPager viewPager;

  @Bind(R.id.feed_tabs)
  TabLayout tabLayout;

  private ArcMenuFragment fragment = null;

  private android.support.v4.app.FragmentManager manager = null;

  private android.support.v4.app.FragmentTransaction ft;

  private FeedPagerAdapter feedPagerAdapter;

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
    // Dagger init
    FeedActivityComponent.Initializer.init(this).inject(this);

    super.onCreate(savedInstanceState);
    feedActivityPresenter.attachView(this);
    init();
  }

  @Override
  public void init() {
    setContentView(R.layout.activity_feed);

    ButterKnife.bind(this);

    feedPagerAdapter = new FeedPagerAdapter(getSupportFragmentManager());
    feedActivityPresenter.setupViewPager(viewPager);
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
    feedActivityPresenter.addDrawerItems();

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setTitle("");
    feedActivityPresenter.setupDrawer();
    mDrawerToggle.syncState();
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

  @Override
  protected void onDestroy() {
    super.onDestroy();
    feedActivityPresenter.detachView();
  }

  public NavDrawerModel getNavDrawerModel() {
    return navDrawerModel;
  }

  public void setNavDrawerModel(NavDrawerModel navDrawerModel) {
    this.navDrawerModel = navDrawerModel;
  }

  @Override
  public Context getContext() {
    return this.getBaseContext();
  }

  @Override
  public RecyclerView.Adapter getmAdapter() {
    return mAdapter;
  }

  @Override
  public void setmAdapter(RecyclerView.Adapter mAdapter) {
    this.mAdapter = mAdapter;
  }

  @Override
  public RecyclerView getmRecyclerView() {
    return mRecyclerView;
  }

  @Override
  public ActionBarDrawerToggle getmDrawerToggle() {
    return mDrawerToggle;
  }

  @Override
  public void setmDrawerToggle(ActionBarDrawerToggle mDrawerToggle) {
    this.mDrawerToggle = mDrawerToggle;
  }

  @Override
  public DrawerLayout getmDrawerLayout() {
    return mDrawerLayout;
  }

  @Override
  public void setmDrawerLayout(DrawerLayout mDrawerLayout) {
    this.mDrawerLayout = mDrawerLayout;
  }

  @Override
  public FeedPagerAdapter getFeedPagerAdapter() {
    return feedPagerAdapter;
  }

  @Override
  public AppCompatActivity getAppCompatActivity(){
      return this;
  }

}
