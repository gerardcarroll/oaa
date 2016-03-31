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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class CommunitiesActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setUp(){

        // Login as guest
        EspressoTestSetup.loginAsGuest();
    }

    @Test
    public void testCommunitiesRendering() {

        // Check Communities recycler view is displayed
        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecyclerViewScroll(){

        // Check Communities recycler view is scrollable
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.scrollToPosition(11));
    }

    @Test
    public void testCommunityMenItemIsDisplayed(){

        // Scroll to bottom of Communities recycler view before checking for Watches item
        onView(withId(R.id.rvCommunities)).check(matches(hasDescendant(withText("Men"))));
    }

    @Test
    public void testCommunityWatchesItemIsDisplayed(){

        // Scroll to bottom of Communities recycler view before checking for Watches item
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.scrollToPosition(17));
        onView(withId(R.id.rvCommunities)).check(matches(hasDescendant(withText("Watches"))));
    }

    @Test
    public void testContinueButtonIsDisplayed(){

        // Check progress button is displayed
        onView(withId(R.id.btnCommunitySelection)).check(matches(isDisplayed()));
    }
}
