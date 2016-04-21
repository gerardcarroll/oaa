package com.overstock.android.prototype.espresso.activity;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.BrandActivity;
import com.overstock.android.prototype.espresso.viewaction.HorizontialScrollFragmentScrollToPositionViewAction;

import org.junit.Before;
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
    public ActivityTestRule<BrandActivity> activityRule = new ActivityTestRule<>(BrandActivity.class, true, false);

    @Before
    public void setUp(){
        activityRule.launchActivity(null);
    }

    @Test
    public void testBrandRendering() {
        onView(withId(R.id.brand_activity)).check(matches(isDisplayed()));
    }

    @Test
    public void testBestSellersRecyclerViewScroll() {

        final int position = 9;

        // Swipe up activity to display New Arrivals recycler view
        onView(withId(R.id.brand_activity)).perform(ViewActions.swipeUp());
        onView(withId(R.id.best_sellers_hrv)).check(matches(isDisplayed()));
        onView(allOf(withParent(withId(R.id.best_sellers_hrv)), withId(R.id.rv_horizontal_scroll_frag)))
                .check(matches(isEnabled()));
        onView(allOf(withParent(withId(R.id.best_sellers_hrv)), withId(R.id.rv_horizontal_scroll_frag)))
                .perform(HorizontialScrollFragmentScrollToPositionViewAction.scrollToPosition(position));

        //https://stackoverflow.com/questions/35272953/espresso-scrolling-not-working-when-nestedscrollview-or-recyclerview-is-in-coor
        //
        //  click action on a recycler view item, with lout as : CoordinatorLayout -> NestedScrollView -> Recycler

        //        onView(allOf(withParent(withId(R.id.best_sellers_hrv)), withId(R.id.rv_horizontal_scroll_frag)))
        //                .perform(HorizontialScrollFragmentActionOnItemAtPositionViewAction.actionOnItemAtPosition(position,
        //                        HorizontialScrollFragmentGeneralClickAction.click()));
    }

    @Test
    public void testNewArrivalsRecyclerViewScroll(){

        final int position = 3;

        // Swipe up activity to display New Arrivals recycler view
        onView(withId(R.id.brand_activity)).perform(ViewActions.swipeUp());
        onView(withId(R.id.new_arrivals_hrv)).check(matches(isDisplayed()));
        onView(allOf(withParent(withId(R.id.new_arrivals_hrv)), withId(R.id.rv_horizontal_scroll_frag)))
                .check(matches(isEnabled()));
        onView(allOf(withParent(withId(R.id.best_sellers_hrv)), withId(R.id.rv_horizontal_scroll_frag)))
                .perform(HorizontialScrollFragmentScrollToPositionViewAction.scrollToPosition(position));

    }
}
