package com.overstock.android.prototype.espresso.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

/**
 * Created by gcarroll on 26/04/2016.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityInstrumentationTest_Email {

  private static final String uniqueID = UUID.randomUUID().toString();

  private final String newUsername = uniqueID + "@gmail.com";

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(HomeActivity.class, true, false);

  @Before
  public void setUp() {
    activityTestRule.launchActivity(null);
  }

  @Test
  public void testEmailSignIn_CancelPressed() {
    navigateToSignInPage();
    checkElementsOfSignInPageDisplayed();
    // Click Cancel
    onView(withId(R.id.btn_email_sign_in_cancel)).perform(click());
    // Check Connect with Email button is displayed
    onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));
  }

  @Test
  public void testEmailSignIn_Successful() {
    navigateToSignInPage();
    checkElementsOfSignInPageDisplayed();
    // Enter username/email
    onView(withId(R.id.et_username)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_username)));
    // Enter password
    onView(withId(R.id.et_password)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_user_password)));
    // Close keyboard
    Espresso.closeSoftKeyboard();
    // Click Sign In
    onView(withId(R.id.btn_sign_in)).perform(click());
    // wait for sign in
    SystemClock.sleep(2000L);
    // verify communities page now shown
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
  }

  @Test
  public void testEmailSignIn_UnSuccessful() {
    navigateToSignInPage();
    checkElementsOfSignInPageDisplayed();
    // Enter username/email
    onView(withId(R.id.et_username)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_username)));
    // Enter incorrect password
    onView(withId(R.id.et_password)).perform(clearText(), typeText("zzzz"));
    // Close keyboard
    Espresso.closeSoftKeyboard();
    // Click Sign In
    onView(withId(R.id.btn_sign_in)).perform(click());
    // wait for sign in
    SystemClock.sleep(2000L);
    // Check Invalid Username/Password toast is displayed
    onView(withText("Username or Password invalid."))
        .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
        .check(matches(isDisplayed()));
  }

  @Test
  public void testEmailSignUp_Successful() {
    navigateToSignUpPage();
    onView(withId(R.id.et_username)).perform(clearText(), typeText(newUsername));
    // Enter password
    onView(withId(R.id.et_password)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_user_password)));
    // Enter password confirm
    onView(withId(R.id.et_confirm_password)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_user_password)));
    // Close keyboard
    Espresso.closeSoftKeyboard();
    // Click Sign In
    onView(withId(R.id.btn_sign_up)).perform(click());
    // wait for sign in
    SystemClock.sleep(2000L);
    // verify communities page now shown
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
  }

  @Test
  public void testEmailSignUp_UnSuccessful_UserAlreadyExists() {
    navigateToSignUpPage();
    // Enter username/email
    onView(withId(R.id.et_username)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_username)));
    // Enter password
    onView(withId(R.id.et_password)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_user_password)));
    // Enter password confirm
    onView(withId(R.id.et_confirm_password)).perform(clearText(),
      typeText(activityTestRule.getActivity().getString(R.string.parse_test_user_password)));
    // Close keyboard
    Espresso.closeSoftKeyboard();
    // Click Sign In
    onView(withId(R.id.btn_sign_up)).perform(click());
    // wait for sign in
    SystemClock.sleep(2000L);
    // Check already exists toast is displayed
    onView(withText("Unsuccessful Sign Up using Email. Account already exists for this username"))
        .inRoot(withDecorView(not(activityTestRule.getActivity().getWindow().getDecorView())))
        .check(matches(isDisplayed()));
  }

  @Test
  public void testEmailSignUp_CancelPressed() {
    navigateToSignUpPage();
    // Click Cancel on SIgn up page
    onView(withId(R.id.btn_cancel)).perform(click());
    // Check Sign in page is displayed
    onView(withId(R.id.txt_email_sign_up_link)).check(matches(isDisplayed()));
  }

  private void checkElementsOfSignInPageDisplayed() {
    // Check elements of view are displayed
    onView(withId(R.id.et_username)).check(matches(isDisplayed()));
    onView(withId(R.id.et_password)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_email_sign_in_cancel)).check(matches(isDisplayed()));
    onView(withId(R.id.btn_sign_in)).check(matches(isDisplayed()));
    onView(withId(R.id.txt_email_sign_up_link)).check(matches(isDisplayed()));
  }

  private void navigateToSignInPage() {
    // Check Connect with Email button is displayed
    onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));
    // Click Connect With Email button
    onView(withId(R.id.email_login_btn)).perform(click());
  }

  public void navigateToSignUpPage() {
    navigateToSignInPage();
    // Check sign up button displayed
    onView(withId(R.id.txt_email_sign_up_link)).check(matches(isDisplayed()));
    // Click sign up
    onView(withId(R.id.txt_email_sign_up_link)).perform(click());
  }
}
