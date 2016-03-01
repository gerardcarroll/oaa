package com.overstock.android.prototype;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;

import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.matcher.CustomMatcher;

/**
 * @author LeeMeehan Created on 01-Mar-16.
 */
public class HomeActivityInstrumentationTest {

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);

  @Test
  public void validateFaceBookLoginButtonClicked() {
    onView(withId(R.id.facebook_login_btn)).perform(click());
    onView(withText(R.string.faceBook_login_toast)).inRoot(CustomMatcher.isToast()).check(matches(isDisplayed()));
  }

  @Test
  public void validateGooglePlusLoginButtonClicked() {
    onView(withId(R.id.googlePlus_login_btn)).perform(click());
    // TODO validate that login has occurred.
  }

  @Test
  public void validateGuestLoginButtonClicked() {
    onView(withId(R.id.guest_login_btn)).perform(click());
    onView(withText(R.string.guest_login_toast)).inRoot(CustomMatcher.isToast()).check(matches(isDisplayed()));
  }

}
