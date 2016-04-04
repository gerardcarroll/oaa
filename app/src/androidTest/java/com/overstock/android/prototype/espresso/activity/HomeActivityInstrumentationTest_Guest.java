package com.overstock.android.prototype.espresso.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

/**
 * @author LeeMeehan Created on 01-Mar-16.
 */
public class HomeActivityInstrumentationTest_Guest {

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

  @Test
  public void validateGuestLoginButtonClicked() {
    onView(withId(R.id.guest_login_btn)).perform(click());
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
    pressBack();//Press Back key.
    onView(withId(R.id.guest_login_btn)).check(matches(isDisplayed()));//Assert that we have rooted to the Login Page.
  }

}
