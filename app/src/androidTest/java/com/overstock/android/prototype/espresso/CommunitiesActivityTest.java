package com.overstock.android.prototype.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

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
public class CommunitiesActivityTest {

    int numCommunitiesColumns = 2;

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testGridColumnLayout(){

        onView(withId(R.id.guest_login_btn)).perform(click());

        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));

        //GridLayoutManager gridLayoutManager = onView(withId(R.id.rvCommunities))

        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.scrollTo(withText("Watches")));



    }
}
