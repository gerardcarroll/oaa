package com.overstock.android.prototype.espresso.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.model.Product;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rconnolly on 3/24/2016.
 */

@RunWith(AndroidJUnit4.class)
public class ProductDetailActivityTest {

    @Rule
    public ActivityTestRule<ProductDetailActivity> activityRule = new ActivityTestRule<>(ProductDetailActivity.class, true, false);

    @Before
    public void setUp() throws Exception {

        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

        URL url = new URL("https://images-common.test.overstock.com/images/products/T924666.jpg");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestProperty("connection", "close");
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap bitmapExtra = BitmapFactory.decodeStream(input);
        connection.disconnect();
        final Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("image", bitmapExtra);
        intent.putExtra("parcel", Parcels.wrap(new Product(251790, "L924666.jpg", "P924666.jpg", "Invicta Men's 9212 Speedway GS Chronograph Watch", 95.58f)));

        activityRule.launchActivity(intent);

    }
    @Test
    public void testProductDetailRendering(){

        // Check Product Detail activity is displayed
        onView(withId(R.id.product_detail_product_name)).check(matches(withText("Invicta Men's 9212 Speedway GS Chronograph Watch")));
        onView(withId(R.id.product_detail_product_name)).perform(ViewActions.swipeUp());
        onView(withId(R.id.slider)).check(matches(isDisplayed()));
        onView(withId(R.id.slider)).check(ViewAssertions.matches(Matchers.withCurrentPositiom(1)));
        onView(withId(R.id.slider)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.slider)).perform(ViewActions.swipeLeft());
        onView(withId(R.id.slider)).check(ViewAssertions.matches(Matchers.withCurrentPositiom(3)));
        onView(withId(R.id.slider)).perform(ViewActions.swipeRight());
        onView(withId(R.id.slider)).check(ViewAssertions.matches(Matchers.withCurrentPositiom(2)));
    }
}

class Matchers {
    public static Matcher<View> withCurrentPositiom (final int position) {
        return new TypeSafeMatcher<View>() {
            @Override public boolean matchesSafely (final View view) {
                return ((SliderLayout) view).getCurrentPosition() == position;
            }

            @Override public void describeTo (final Description description) {
                description.appendText ("SliderLayout should be at position " + position);
            }
        };
    }
}