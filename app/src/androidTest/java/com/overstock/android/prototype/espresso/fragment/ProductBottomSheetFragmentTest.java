package com.overstock.android.prototype.espresso.fragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.model.Product;

/**
 * @author LeeMeehan.
 * @since Created on 15-Apr-16.
 */
@RunWith(AndroidJUnit4.class)
public class ProductBottomSheetFragmentTest {

  @Rule
  public ActivityTestRule<ProductDetailActivity> activityRule = new ActivityTestRule<>(ProductDetailActivity.class,
      true, false);

  @Before
  public void setUp() throws Exception {

    final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

    URL url = new URL("https://images-common.test.overstock.com/images/products/T13729834.jpg");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoInput(true);
    connection.setRequestProperty("connection", "close");
    connection.connect();
    InputStream input = connection.getInputStream();
    Bitmap bitmapExtra = BitmapFactory.decodeStream(input);
    connection.disconnect();
    final Intent intent = new Intent(context, ProductDetailActivity.class);
    intent.putExtra("image", bitmapExtra);
    intent.putExtra("parcel", Parcels.wrap(new Product(6053246, "L13729834.jpg", "P13729834.jpg",
        "Latte iPearl S Pink 4 GB 1.8-inch LCD MP4 Player", 26.26f)));

    activityRule.launchActivity(intent);

  }

  @Test
  public void testProductBottomSheet() throws InterruptedException {
    // Check Add to chart fab button is displayed
    Thread.sleep(2000);
    onView(withId(R.id.btn_buy)).check(matches(isDisplayed()));

    // Click on add to chart button to open product bottom sheet
    onView(withId(R.id.btn_buy)).perform(click());

    onView(withId(R.id.quantity_add)).perform(click());
    onView(withId(R.id.quantity_indicator)).check(matches(withText("1")));
  }

}
