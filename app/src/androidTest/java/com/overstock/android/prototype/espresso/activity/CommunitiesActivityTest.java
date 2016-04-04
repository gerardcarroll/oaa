package com.overstock.android.prototype.espresso.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.CommunitiesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Espresso tests to check the general functionality of the Communities activity.
 *
 * @author rconnolly Created on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class CommunitiesActivityTest {

  @Rule
  public ActivityTestRule<CommunitiesActivity> activityRule = new ActivityTestRule<>(CommunitiesActivity.class);

  @Test
  public void testCommunitiesRendering() {

    // Check Communities recycler view is displayed
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
  }

  @Test
  public void testRecyclerViewScroll() {

    // Check Communities recycler view is scrollable
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.scrollToPosition(11));
  }

  @Test
  public void testCommunityFirstItemIsDisplayed() {

    // Click on 1st item in recycler view before checking for Men item
    onView(withId(R.id.rvCommunities)).check(matches(hasDescendant(withText("Men"))));
  }

  @Test
  public void testCommunityLastItemIsDisplayed() {

    // Scroll to bottom of Communities recycler view before checking for Watches item
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.scrollToPosition(17));
    onView(withId(R.id.rvCommunities)).check(matches(hasDescendant(withText("Watches"))));
  }

  @Test
  public void testProgressButtonIsDisplayed() {

    // Check progress button is displayed
    onView(withId(R.id.btnCommunitySelection)).check(matches(isDisplayed()));
  }

  @Test
  public void testProgressButtonFunctionality() {

    // Check Communities recycler view is displayed
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));

    // Check progress button is displayed
    onView(withId(R.id.btnCommunitySelection)).check(matches(isDisplayed()));

    // Check progress button is not yet enabled
    onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));

    // Check 1st item in recycler view
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    // Check progress button is not yet enabled
    onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));

    // Check 2nd item in recycler view
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    // Check progress button is not yet enabled
    onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));

    // Check 3rd item in recycler view
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

    // Check progress button is now enabled
    onView(withId(R.id.btnCommunitySelection)).check(matches(isEnabled()));

    // Click on now enabled progress button
    onView(withId(R.id.btnCommunitySelection)).perform(click());

    // Check Feed Activity is now displayed
    onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
  }
}
