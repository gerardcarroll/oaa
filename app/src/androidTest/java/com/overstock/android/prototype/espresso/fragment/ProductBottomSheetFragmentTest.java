package com.overstock.android.prototype.espresso.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.ProductDetailActivity;
import com.overstock.android.prototype.model.Options;
import com.overstock.android.prototype.model.Product;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

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

    URL url = new URL("http://ak1.ostkcdn.com/images/products/T13729834.jpg");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setDoInput(true);
    connection.setRequestProperty("connection", "close");
    connection.connect();
    InputStream input = connection.getInputStream();
    Bitmap bitmapExtra = BitmapFactory.decodeStream(input);
    connection.disconnect();
    final Intent intent = new Intent(context, ProductDetailActivity.class);
    intent.putExtra("image", bitmapExtra);
    intent.putExtra("parcel",
      Parcels.wrap(new Product(8939543, "8939543/Alternative-Apparel-Mens-Eco-Jersey-Football-T-Shirt-L16153098.jpg",
          "8939543/Alternative-Apparel-Mens-Eco-Jersey-Football-T-Shirt-P16153098.jpg",
          "Alternative Apparel Men's Eco-Jersey Football T-Shirt", 28.89f)));

    activityRule.launchActivity(intent);

  }

  @Test
  public void testProductBottomSheet() throws InterruptedException {
    // Check Add to chart fab button is displayed
    Thread.sleep(2000);
    onView(withId(R.id.btn_buy)).check(matches(isDisplayed()));

    // Click on add to chart button to open product bottom sheet
    onView(withId(R.id.btn_buy)).perform(click());

    onView(withId(R.id.bottom_sheet)).check(matches(isDisplayed()));

    onView(withId(R.id.product_options_txt)).check(matches(withText("Product Options")));
    // Im swiping up on product_options_txt because it is 100% visible. whereas the product bottom sheet is less than
    // 50% visible.
    onView(withId(R.id.product_options_txt)).perform(swipeUp());

    onView(withId(R.id.options_spinner)).check(matches(isDisplayed()));
    onView(withId(R.id.options_spinner)).perform(click());
    onData(Matchers.instanceOf(Options.class)).atPosition(3).inRoot(isPlatformPopup()).perform(click());
    onView(withId(R.id.totalPrice_txt)).check(matches(withText("$ 30.99")));
    // Assert that quantity controls are working.
    onView(withId(R.id.quantity_add)).perform(click());
    onView(withId(R.id.quantity_indicator)).check(matches(withText("2")));
    onView(withId(R.id.totalPrice_txt)).check(matches(withText("$ 61.98")));
    onView(withId(R.id.quantity_remove)).perform(click());
    onView(withId(R.id.quantity_indicator)).check(matches(withText("1")));
    onView(withId(R.id.totalPrice_txt)).check(matches(withText("$ 30.99")));

    // Confirming that the discount has been applied.
    onView(withId(R.id.rewards_btn_apply)).perform(click());
    onView(withId(R.id.totalPrice_txt)).check(matches(withText("$ 18.49")));

    // assert that popup has been dismissed.
    onView(withId(R.id.btn_pay_credit_card)).perform(click());
    onView(withId(R.id.bottom_sheet)).check(doesNotExist());
  }

}
