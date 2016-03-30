package com.overstock.android.prototype.espresso.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.CommunitiesActivity;

/**
 * Created by itowey on 23/03/16.
 */
@RunWith(AndroidJUnit4.class)
public class CommunitiesActivityTest {

  @Rule
  public ActivityTestRule<CommunitiesActivity> activityRule = new ActivityTestRule<>(CommunitiesActivity.class);

  @Test
  public void testRendering() {
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
  }

}
