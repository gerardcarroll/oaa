package com.overstock.android.prototype.espresso.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
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
 * Created by rconnolly on 5/4/2016.
 */

@RunWith(AndroidJUnit4.class)
public class ProductDetailsSummaryFragmentTest {

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

        // Check Name view is displayed
        onView(withId(R.id.product_detail_product_name)).check(matches(isDisplayed()));

        // Check Price view is displayed
        onView(withId(R.id.product_detail_product_price)).check(matches(isDisplayed()));

        // Check Review views are displayed
        onView(withId(R.id.product_detail_product_review)).check(matches(isDisplayed()));
        onView(withId(R.id.review_star)).check(matches(isDisplayed()));
    }

    @Test
    public void testSummaryDetails(){
        SystemClock.sleep(1000);

        // Check Product details
        onView(withId(R.id.product_detail_product_name)).check(matches(withText("Green Bay Packers 68-inch Economy Grill Cover")));
        onView(withId(R.id.product_detail_product_price)).check(matches(withText("$ 31.52")));
    }

}
