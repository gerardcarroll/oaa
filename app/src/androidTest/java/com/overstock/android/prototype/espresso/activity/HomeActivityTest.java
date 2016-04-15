package com.overstock.android.prototype.espresso.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, true, false);

    @Before
    public void setUp() {
        activityRule.launchActivity(null);
    }

    @Test
    public void testHomeRendering() {

        // Check Feed recycler view is displayed
        onView(withId(R.id.facebook_login_btn)).check(matches(isDisplayed()));
    }

}
