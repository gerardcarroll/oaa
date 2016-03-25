package com.overstock.android.prototype.espresso.activity;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author LeeMeehan Created on 01-Mar-16.
 */
public class HomeActivityInstrumentationTest_Guest {

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

  @Before
  public void setUp(){
    Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
  }

  @Test
  public void validateGuestLoginButtonClicked() {
    onView(withId(R.id.guest_login_btn)).perform(click());
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
  }

}
