package com.overstock.android.prototype.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author LeeMeehan Created on 29-Mar-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class FeedActivityTest {

  private FeedActivity feedActivity;

  @Before
  public void setUp() {
    feedActivity = Robolectric.buildActivity(FeedActivity.class).create().start().resume().visible().get();
  }

  @After
  public void tearDown(){
    Robolectric.reset();
  }

  @Test
  public void testFeedActivity_Creation() {
    assertNotNull(feedActivity);
    TabLayout feedTabLayout = (TabLayout) feedActivity.findViewById(R.id.feed_tabs);
    assertNotNull(feedTabLayout);
    assertEquals(3, feedTabLayout.getTabCount());
    ViewPager feedViewPager = (ViewPager) feedActivity.findViewById(R.id.feed_viewpager);
    assertNotNull(feedViewPager);
    assertNotNull(feedViewPager.getAdapter());
  }

}
