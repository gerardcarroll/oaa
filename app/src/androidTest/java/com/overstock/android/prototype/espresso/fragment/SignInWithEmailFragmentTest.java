package com.overstock.android.prototype.espresso.fragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

/**
 * Created by gcarroll on 20/04/2016.
 */
@RunWith(AndroidJUnit4.class)
public class SignInWithEmailFragmentTest {

  @Rule
  public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, true, false);

  @Before
  public void setUp() {
    activityRule.launchActivity(null);
  }

  @Test
  public void testEmailSignInPageRendering() {
    // Click Connect With Email button
    onView(withId(R.id.email_login_btn)).perform(click());
    // Check elements of view are displayed
    onView(withId(R.id.et_username)).check(matches(isDisplayed()));
    onView(withId(R.id.et_password)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_email_sign_in_cancel)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_sign_in)).check(matches(isDisplayed()));
    onView(withId(R.id.txt_email_sign_up)).check(matches(isDisplayed()));
  }

}
