package com.overstock.android.prototype.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.OAppPrototypeApplicationMockRule;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.component.FeedActivityComponent;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.module.FeedActivityModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author LeeMeehan Created on 29-Mar-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, application =  com.overstock.android.prototype.TestOAppPrototypeApplication.class)
@RunWith(RobolectricGradleTestRunner.class)
public class FeedActivityTest {

  private FeedActivity feedActivity;

  @Rule
  public OAppPrototypeApplicationMockRule oAppPrototypeApplicationMockRule = new OAppPrototypeApplicationMockRule();

  @Rule
  public final DaggerMockRule<FeedActivityComponent> mockRule1 = new DaggerMockRule<>(FeedActivityComponent.class,
          new FeedActivityModule()).addComponentDependency(ApplicationComponent.class,
          new ApplicationModule(new OAppPrototypeApplication()));

  @Before
  public void setUp() {
    mockRule1.addComponentDependency(ApplicationComponent.class);
    feedActivity  =  Robolectric.buildActivity(FeedActivity.class).create().start().resume().visible().get();
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
