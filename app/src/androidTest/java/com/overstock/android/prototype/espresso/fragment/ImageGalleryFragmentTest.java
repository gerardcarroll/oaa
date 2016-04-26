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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author RayConnolly, created on 4/26/2016.
 */

@RunWith(AndroidJUnit4.class)
public class ImageGalleryFragmentTest {

    @Rule
    public ActivityTestRule<ProductDetailActivity> activityRule = new ActivityTestRule<>(ProductDetailActivity.class, true, false);

    @Before
    public void setUp() throws IOException {

        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        URL url = new URL("http://images-common.test.overstock.com/images/products/9260610/T16428339.jpg");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestProperty("connection", "close");
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap bitmapExtra = BitmapFactory.decodeStream(input);
        connection.disconnect();
        final Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("image", bitmapExtra);
        intent.putExtra(ProductDetailsFragment.PRODUCT_DETAILS_PARCEL, Parcels.wrap(new Product(9260610, "9260610/L16428339.jpg", "9260610/P16428339.jpg", "Fanmats NFL Grill Mat", 28.89f)));

        activityRule.launchActivity(intent);

    }

    @Test
    public void testProductDetailRendering() {

        SystemClock.sleep(1000);

        // Check Image Gallery fragment is displayed
        onView(withId(R.id.slider)).check(matches(isDisplayed()));

        // Check product details are displayed
        onView(withId(R.id.product_detail_product_name)).check(matches(withText("Fanmats NFL Grill Mat")));
        onView(withId(R.id.product_detail_product_price)).check(matches(withText("$ 28.89")));
        onView(withId(R.id.product_detail_product_name)).perform(ViewActions.swipeUp());

        // Check Image Gallery swipe actions
        onView(withId(R.id.slider)).check(matches(isDisplayed()));
        onView(withId(R.id.slider)).check(ViewAssertions.matches(SliderMatcher.withCurrentPositiom(0)));
        onView(withId(R.id.slider)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.slider)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.slider)).check(ViewAssertions.matches(SliderMatcher.withCurrentPositiom(2)));
        onView(withId(R.id.slider)).perform(ViewActions.swipeRight());
        onView(withId(R.id.slider)).check(ViewAssertions.matches(SliderMatcher.withCurrentPositiom(1)));
    }
}


