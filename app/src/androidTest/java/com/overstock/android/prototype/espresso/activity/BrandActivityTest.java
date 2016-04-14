package com.overstock.android.prototype.espresso.activity;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class BrandActivityTest {

    @Rule
    public ActivityTestRule<BrandActivity> activityRule = new ActivityTestRule<>(BrandActivity.class);

    @Test
    public void testBrandRendering() {
        onView(withId(R.id.brand_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testBestSellersRecyclerViewScroll() {

        final int position = 9;

        // Swipe up activity to display New Arrivals recycler view
        onView(withId(R.id.brand_activity)).perform(ViewActions.swipeUp());

        // Scroll to position New Arrivals recycler view
        onView(allOf(withId(R.id.rv_horizontal_scroll_frag), withParent(withId(R.id.best_sellers_hrv)))).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                Matcher<View> standardConstraint = isEnabled();
                return standardConstraint;
            }

            @Override
            public String getDescription() {
                return "scroll RecyclerView to position: " + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.scrollToPosition(position);
                uiController.loopMainThreadUntilIdle();
            }
        });
    }

    @Test
    public void testNewArrivalsRecyclerViewScroll(){

        final int position = 3;

        // Swipe up activity to display New Arrivals recycler view
        onView(withId(R.id.brand_activity)).perform(ViewActions.swipeUp());

        // Scroll to position New Arrivals recycler view

        onView(allOf(withId(R.id.rv_horizontal_scroll_frag), withParent(withId(R.id.new_arrivals_hrv)))).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                Matcher<View> standardConstraint = isEnabled();
                return standardConstraint;
            }

            @Override
            public String getDescription() {
                return "scroll RecyclerView to position: " + position;
            }

            @Override
            public void perform(UiController uiController, View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.scrollToPosition(position);
                uiController.loopMainThreadUntilIdle();
            }
        });
    }
}
