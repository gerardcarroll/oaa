package com.overstock.android.prototype.espresso.activity;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

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
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class BrandActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp(){

        // Login as guest
        EspressoTestSetup.loginAsGuest();

        // Navigate through communities activity process
        EspressoTestSetup.chooseCommunities();

        // Check Feed tabs are displayed before clicking on Feed tab
        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());

        // Check Feed recycler view is displayed
        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));

        // Click on 1st Feed item in list
        onView(withId(R.id.rv_feed)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void testBrandRendering() {

        // Check Brand Best Sellers recycler view is displayed
        onView(withId(R.id.best_sellers)).check(matches(isDisplayed()));
    }

    @Test
    public void testBestSellersRecyclerViewScroll(){

        // Scroll to end of Best Sellers recycler view
        onView(withId(R.id.best_sellers)).perform(RecyclerViewActions.scrollToPosition(30));
    }

    @Test
    public void testToastIsDisplayedOnScroll(){

        // Scroll to to 25th item in Best Sellers recycler view
        onView(withId(R.id.best_sellers)).perform(RecyclerViewActions.scrollToPosition(25));

        // Check Toast is displayed on scrolling to 25th item
        View activityDecorView = activityRule.getActivity().getWindow().getDecorView();
        onView(withText("HI THERE")).inRoot(withDecorView(not(activityDecorView))).check(matches(isDisplayed()));
    }

    @Test
    public void testNewArrivalsRecyclerViewScroll(){

        // Swipe up activity to display New Arrivals recycler view
        onView(withId(R.id.brand_activity)).perform(ViewActions.swipeUp());

        // Scroll to end of New Arrivals recycler view
        onView(withId(R.id.new_arrivals)).perform(RecyclerViewActions.scrollToPosition(30));
    }
}
