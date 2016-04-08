package com.overstock.android.prototype.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.adapters.FeedPagerAdapter;
import com.overstock.android.prototype.fragment.FeedFragment;
import com.overstock.android.prototype.fragment.MyLocationFragment;
import com.overstock.android.prototype.fragment.TrendingFragment;

public class FeedActivity extends AppCompatActivity {

  @Bind(R.id.feed_viewpager)
  ViewPager viewPager;

  @Bind(R.id.feed_tabs)
  TabLayout tabLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed);
    ButterKnife.bind(this);
    setupViewPager(viewPager);
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    FeedPagerAdapter adapter = new FeedPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new FeedFragment(), getString(R.string.my_feed_tab));
    adapter.addFragment(new TrendingFragment(), getString(R.string.trending_tab));
    adapter.addFragment(new MyLocationFragment(), getString(R.string.my_location_tab));
    viewPager.setAdapter(adapter);
  }
}
