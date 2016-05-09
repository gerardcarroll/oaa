package com.overstock.android.prototype.espresso.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.espresso.matcher.SliderMatcher;
import com.overstock.android.prototype.fragment.ProductDetailsFragment;
import com.overstock.android.prototype.model.Product;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rconnolly on 5/6/2016.
 */
//TODO FIX THIS TEST. FAILING DUE TO VIABILITY BELOW 80% THRESHOLD.
@RunWith(AndroidJUnit4.class)
@Ignore
public class MoreInformationFragmentTest {

    private static final String SHIPPING_TITLE = "Shipping Information";
    private static final String RETURNS_TITLE = "Returns";
    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla pharetra quis mauris et congue. Mauris lacinia porttitor vestibulum. Donec id ipsum at tortor iaculis pulvinar sed id nunc. Mauris lacinia porttitor vestibulum. Donec id ipsum at tortor iaculis pulvinar sed id nunc.";

    @Rule
    public ActivityTestRule<ProductDetailActivity> activityRule = new ActivityTestRule<>(ProductDetailActivity.class, true, false);

    @Before
    public void setUp() throws IOException {

        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        URL url = new URL("http://images-common.test.overstock.com/images/products/9175867/Green-Bay-Packers-68-inch-Economy-Grill-Cover-T16351980.jpg");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestProperty("connection", "close");
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap bitmapExtra = BitmapFactory.decodeStream(input);
        connection.disconnect();
        final Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("image", bitmapExtra);
        intent.putExtra(ProductDetailsFragment.PRODUCT_DETAILS_PARCEL, Parcels.wrap(new Product(9175867, "9175867/Green-Bay-Packers-68-inch-Economy-Grill-Cover-L16351980.jpg", "9175867/Green-Bay-Packers-68-inch-Economy-Grill-Cover-P16351980.jpg", "9175867/Green-Bay-Packers-68-inch-Economy-Grill-Cover-T16351980.jpg", "Green Bay Packers 68-inch Economy Grill Cover", 31.52f)));

        activityRule.launchActivity(intent);
    }

    @Test
    public void testViewsRendering() {

        SystemClock.sleep(1000);

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).check(matches(isDisplayed()));

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).perform(click());

        // Check More Information viewpager is displayed
        onView(withId(R.id.viewpager_more_info)).check(matches(isDisplayed()));
    }

    @Test
    public void moreInfoDetailsTest(){

        SystemClock.sleep(1000);

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).check(matches(isDisplayed()));

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).perform(click());

        // Check Product Name is displayed
        onView(withId(R.id.details_product_name)).check(matches(isDisplayed()));

        // Check Details button is displayed
        onView(withId(R.id.btn_details)).check(matches(isDisplayed()));

        // Check Shipping/Returns button is displayed
        onView(withId(R.id.btn_shipping_returns)).check(matches(isDisplayed()));

        // Check Description title is displayed
        onView(withId(R.id.details_title)).check(matches(isDisplayed()));

        SystemClock.sleep(1000);

        // Check Description text is displayed
        onView(withId(R.id.more_info_product_detail_content)).check(matches(isDisplayed()));
    }

    @Test
    public void moreInfoShippingReturnsTest(){

        SystemClock.sleep(1000);

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).check(matches(isDisplayed()));

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).perform(click());

        // Click on Shipping/Returns button
        onView(withId(R.id.btn_shipping_returns)).check(matches(isDisplayed()));

        SystemClock.sleep(1000);

        // Check Shipping/Returns title is displayed
        onView(withId(R.id.shipping_title)).check(matches(withText(SHIPPING_TITLE)));

        // Check Shipping text is displayed
        onView(withId(R.id.shipping_lorem_ipsum_text)).check(matches(withText(LOREM_IPSUM)));

        // Check Returns title is displayed
        onView(withId(R.id.returns_title)).check(matches(withText(RETURNS_TITLE)));

        // Check Returns text is displayed
        onView(withId(R.id.returns_lorem_ipsum_text)).check(matches(withText(LOREM_IPSUM)));
    }

    // TODO Fix issue with SwipeUp on ProductName view
    @Ignore
    @Test
    public void viewPagerSwipeTest(){

        SystemClock.sleep(1000);

        // Check Product Name is displayed
        onView(withId(R.id.details_product_name)).check(matches(isDisplayed()));

        // Perform SwipeUp action on Product Name
        onView(withId(R.id.details_product_name)).perform(ViewActions.swipeUp());

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).check(matches(isDisplayed()));

        // Check More Information link is displayed
        onView(withId(R.id.txt_more_information_link)).perform(click());

        SystemClock.sleep(1000);

        // Check More Information viewpager is displayed
        onView(withId(R.id.viewpager_more_info)).check(matches(isDisplayed()));

        // Check More Information viewpager swipe actions
        onView(withId(R.id.viewpager_more_info)).check(ViewAssertions.matches(SliderMatcher.withCurrentPositiom(0)));
        onView(withId(R.id.viewpager_more_info)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.viewpager_more_info)).check(ViewAssertions.matches(SliderMatcher.withCurrentPositiom(1)));
        onView(withId(R.id.viewpager_more_info)).perform(ViewActions.swipeRight());
        onView(withId(R.id.viewpager_more_info)).check(ViewAssertions.matches(SliderMatcher.withCurrentPositiom(0)));
    }
}
