package com.overstock.android.prototype.activity;

import android.os.Build;

import com.overstock.android.prototype.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

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

  @Test
  public void testFeedActivity_Creation() {
    assertNotNull(feedActivity);
  }

}
