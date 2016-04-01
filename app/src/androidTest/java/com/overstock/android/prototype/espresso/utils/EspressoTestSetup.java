package com.overstock.android.prototype.espresso.utils;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;

import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rconnolly on 3/25/2016.
 */

@RunWith(AndroidJUnit4.class)
public abstract class EspressoTestSetup {

    public static void loginAsGuest(){

        onView(withId(R.id.guest_login_btn)).perform(click());
    }

    public static void chooseCommunities(){

        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.btnCommunitySelection)).perform(click());
    }
}
