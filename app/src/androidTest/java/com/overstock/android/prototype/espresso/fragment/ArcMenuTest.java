package com.overstock.android.prototype.espresso.fragment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.FeedActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Espresso tests to check Arc Menu functionality.
 *
 * @author RayConnolly Created on 4/7/2016.
 */

@RunWith(AndroidJUnit4.class)
public class ArcMenuTest {

    @Rule
    public ActivityTestRule<FeedActivity> activityRule = new ActivityTestRule<>(FeedActivity.class);

    @Test
    public void testFeedRendering() {

        // Check Feed recycler view is displayed
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testArcMenuFabDisplayed() {

        // Check Arc Menu fab button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }

    @Test
    public void testFabBtnOpenArcMenuAction() {

        // Check Arc Menu fab button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Check arc menu is not yet displayed
        onView(withId(R.id.menu_layout)).check(matches(not(isDisplayed())));

        // Click on fab button to open arc menu
        onView(withId(R.id.fab)).perform(click());

        // Check arc menu is now displayed
        onView(withId(R.id.menu_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void testArcMenuButtonsClickActions_Feed() {

        // Check Arc Menu fab button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Check arc menu is not yet displayed
        onView(withId(R.id.menu_layout)).check(matches(not(isDisplayed())));

        // Click on fab button to open arc menu
        onView(withId(R.id.fab)).perform(click());

        // Check arc menu is now displayed
        onView(withId(R.id.menu_layout)).check(matches(isDisplayed()));

        // Click on first arc menu button
        onView(withId(R.id.arcMenuBtn1)).perform(click());

        // Check Feed activity is now displayed
        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));

    }

    @Test
    public void testArcMenuButtonsClickActions_2() {

        // Check Arc Menu fab button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Check arc menu is not yet displayed
        onView(withId(R.id.menu_layout)).check(matches(not(isDisplayed())));

        // Click on fab button to open arc menu
        onView(withId(R.id.fab)).perform(click());

        // Check arc menu is now displayed
        onView(withId(R.id.menu_layout)).check(matches(isDisplayed()));

        // Click on second arc menu button
        onView(withId(R.id.arcMenuBtn2)).perform(click());

    }

    @Test
    public void testArcMenuButtonsClickActions_3() {

        // Check Arc Menu fab button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Check arc menu is not yet displayed
        onView(withId(R.id.menu_layout)).check(matches(not(isDisplayed())));

        // Click on fab button to open arc menu
        onView(withId(R.id.fab)).perform(click());

        // Check arc menu is now displayed
        onView(withId(R.id.menu_layout)).check(matches(isDisplayed()));

        // Click on third arc menu button
        onView(withId(R.id.arcMenuBtn3)).perform(click());

    }

    @Test
    public void testFabBtnCloseArcMenuAction() {

        // Check Arc Menu fab button is displayed
        onView(withId(R.id.fab)).check(matches(isDisplayed()));

        // Check arc menu is not yet displayed
        onView(withId(R.id.menu_layout)).check(matches(not(isDisplayed())));

        // Click on fab button to open arc menu
        onView(withId(R.id.fab)).perform(click());

        // Check arc menu is now displayed
        onView(withId(R.id.menu_layout)).check(matches(isDisplayed()));

        // Click on fab button again to close arc menu
        onView(withId(R.id.fab)).perform(click());

        try {
            Thread.sleep(2000);

            // Check arc menu is not displayed now
            onView(withId(R.id.menu_layout)).check(matches(not(isDisplayed())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
