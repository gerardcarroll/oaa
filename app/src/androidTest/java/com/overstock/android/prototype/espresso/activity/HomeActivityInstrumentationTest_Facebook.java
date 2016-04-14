package com.overstock.android.prototype.espresso.activity;

import android.support.test.rule.ActivityTestRule;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.espresso.matcher.CustomMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author LeeMeehan Created on 01-Mar-16.
 */
public class HomeActivityInstrumentationTest_Facebook {

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class, true, false);

  @Before
  public void setUp(){
    activityTestRule.launchActivity(null);
  }

  @Test
  public void validateFaceBookLoginButtonClicked() {
    onView(withId(R.id.facebook_login_btn)).perform(click());
    onView(withText(R.string.faceBook_login_toast)).inRoot(CustomMatcher.isToast()).check(matches(isDisplayed()));
  }

}
