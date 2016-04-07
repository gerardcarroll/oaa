package com.overstock.android.prototype.espresso.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.FeedActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class NavDrawerTest {

    @Rule
    public ActivityTestRule<FeedActivity> activityRule = new ActivityTestRule<>(FeedActivity.class);

    @Test
    public void testOpenWithBurger() {

        // Check Feed recycler view is displayed
        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));
        onView(withContentDescription("Open navigation drawer")).check(matches(isDisplayed()));
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.circleView)).check(matches(isDisplayed()));

    }

    @Test
    public void testOpenWithSwipe() {

        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_feed)).perform(swipeRight());
        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.circleView)).check(matches(isDisplayed()));

    }

    @Test
    public void testCloseWithSwipe() {

        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_feed)).perform(swipeRight());
        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
        onView(withId(R.id.circleView)).check(matches(isDisplayed()));
        onView(withId(R.id.circleView)).perform(swipeLeft());
        onView(withId(R.id.rv_feed)).check(matches(isDisplayed()));

    }


}
