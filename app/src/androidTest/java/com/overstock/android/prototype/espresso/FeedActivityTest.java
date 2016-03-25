package com.overstock.android.prototype.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

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

        // Login to app as guest
        onView(withId(R.id.guest_login_btn)).perform(click());

        // Navigate through communities activity process
        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.btnCommunitySelection)).perform(click());
    }

    @Test
    public void testFeedRendering() {

        // Check Feed recycler view is displayed
        onView(withId(R.id.rv_feed_communities)).check(matches(isDisplayed()));
    }

    @Test
    public void testFeedScroll(){

        // Check Feed tabs are displayed before clicking on Feed tab
        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());

        // Perform scroll actions on feed recycler view
        onView(withId(R.id.rv_feed_communities)).perform(RecyclerViewActions.scrollToPosition(2));
        onView(withId(R.id.rv_feed_communities)).perform(RecyclerViewActions.scrollToPosition(0));
        onView(withId(R.id.rv_feed_communities)).perform(RecyclerViewActions.scrollToPosition(2));
    }

    @Test
    public void testFeedItemClick(){

        // Check Feed tabs are displayed before clicking on Feed tab
        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());

        // Click on 1st Feed item in list
        onView(withId(R.id.rv_feed_communities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Check Brand activity is now displayed
        onView(withId(R.id.best_sellers)).check(matches(isDisplayed()));
    }
}
