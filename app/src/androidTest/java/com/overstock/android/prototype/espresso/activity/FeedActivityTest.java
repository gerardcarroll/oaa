package com.overstock.android.prototype.espresso.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.espresso.utils.EspressoTestSetup;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class FeedActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp(){

        // Login as guest
        EspressoTestSetup.loginAsGuest();

        // Navigate through communities activity process
        EspressoTestSetup.chooseCommunities();
    }

    @Test
    public void testFeedRendering() {

        // Check Feed recycler view is displayed
        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));
    }

    @Test
    public void testFeedScroll(){

        // Check Feed tabs are displayed before clicking on Feed tab
        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());

        // Check Feed Communities recycler view is displayed
        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));

        // Perform scroll actions on feed recycler view
        onView(withId(R.id.rv_feed)).perform(RecyclerViewActions.scrollToPosition(2));
        onView(withId(R.id.rv_feed)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.rv_feed)).perform(RecyclerViewActions.scrollToPosition(2));
    }

    @Test
    public void testFeedItemClick(){

        // Check Feed tabs are displayed before clicking on Feed tab
        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());

        // Click on 1st Feed item in list
        onView(withId(R.id.rv_feed)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check Brand activity is now displayed
        onView(withId(R.id.best_sellers)).check(matches(isDisplayed()));
    }
}
