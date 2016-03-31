

/**
 * Created by finolacurran on 3/30/2016.
 */

        package com.overstock.android.prototype.uiautomator;

import android.content.Context;
import android.content.Intent;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;


@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class GuestLoginEndToEnd {

    private static final String OApp_PACKAGE
            = "com.overstock.android.prototype";
    private UiDevice mDevice;

    private static final int TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    @Test
    public void startMainActivityFromHomeScreen(){

        // Start from the home screen
        mDevice.pressHome();

        // Click the Apps icon
         UiObject2 AppsIcon = mDevice.findObject(By.res("com.sec.android.app.launcher", "home_allAppsIcon"));
         AppsIcon.click();

        // Click the OApp icon
        mDevice.wait(Until.hasObject(By.pkg("com.sec.android.app.launcher").depth(0)), TIMEOUT);
        UiObject2 OAppPrototypeApp = mDevice.findObject(By.text("O.com App Prototype"));
        OAppPrototypeApp.click();

        // Login as Guest
        mDevice.wait(Until.hasObject(By.res(OApp_PACKAGE, "guest_login_btn")), TIMEOUT);
        UiObject2 ConnectWithGuest = mDevice.findObject(By.res(OApp_PACKAGE, "guest_login_btn"));
        ConnectWithGuest.click();

        // wait until the recycler view for the communities is on the screen
        mDevice.wait(Until.hasObject(By.res("com.overstock.android.prototype", "ivCommunities")), TIMEOUT);

        // select communities
        UiObject recyclerView = mDevice.findObject(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        try {
            recyclerView.getChild(new UiSelector().index(0)).click();
            recyclerView.getChild(new UiSelector().index(2)).click();
            recyclerView.getChild(new UiSelector().index(3)).click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        //scroll on the communities page
        UiScrollable CommunitiesPageScroll = new UiScrollable(new UiSelector().className("android.widget.FrameLayout"));
        try {
            CommunitiesPageScroll.scrollForward();
            CommunitiesPageScroll.scrollBackward();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        // click continue button
        mDevice.wait(Until.hasObject(By.text("Continue")), TIMEOUT);
        // UiObject2 ContinueButton = mDevice.findObject(By.text("Continue ✓"));
        UiObject2 ContinueButton = mDevice.findObject(By.res(OApp_PACKAGE, "btnCommunitySelection"));
        ContinueButton.click();

        //wait until feed image is on the page
        UiObject2 MyFeedPage = mDevice.findObject(By.res(OApp_PACKAGE, "feed_img"));
        mDevice.wait(Until.hasObject(By.res(OApp_PACKAGE, "feed_img")), TIMEOUT);

        //Click on Trending tab on feed page
        UiObject2 TrendingTab = mDevice.findObject(By.text("Trending"));
        TrendingTab.click();

        /* Click on My Location tab on feed page */
        UiObject2 MyLocationTab = mDevice.findObject(By.text("My Location"));
        TrendingTab.click();

        //Click on My Feed tab on feed page
        UiObject2 MyFeedTab = mDevice.findObject(By.text("My Feed"));
        MyFeedTab.click();

        //scroll on My Feed page
        UiScrollable FeedPageScroll = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        try {
            FeedPageScroll.scrollForward();
            FeedPageScroll.scrollBackward();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        //scroll on the my feed page and then click on it
        //UiScrollable FeedPageScroll = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        //UiScrollable FeedPageScroll = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        //UiScrollable FeedPageScroll = new UiScrollable(new UiSelector().scrollable(true));
        //FeedPageScroll.scrollForward();
        //FeedPageImage.click();

        //MyFeedPage = mDevice.findObject(By.res("com.overstock.android.prototype","feed_img"));

        //MyFeedPage.click();

        //scrollForward();

        //Click on feed page
        mDevice.wait(Until.hasObject(By.res(OApp_PACKAGE, "feed_img")), TIMEOUT);
        UiObject2 FeedPageImage = mDevice.findObject(By.res(OApp_PACKAGE,"feed_img"));
        //mDevice.wait(Until.hasObject(By.res("com.overstock.android.prototype", "feed_img")), 10000);
        FeedPageImage.click();

        //wait until the user is on Brand page and Click on header on Brand page
        mDevice.wait(Until.hasObject(By.res(OApp_PACKAGE,"header")), TIMEOUT);
        UiObject2 BrandPageHeader = mDevice.findObject(By.res(OApp_PACKAGE,"header"));
        // mDevice.wait(Until.hasObject(By.res(OApp_PACKAGE,"header")), TIMEOUT);
        BrandPageHeader.click();

        //Click a product image on Brand page
        UiObject2 BrandProduct = mDevice.findObject(By.res(OApp_PACKAGE,"product_card_img"));
        BrandProduct.click();


        mDevice.pressHome();

    }



    @Before
    public void setUp() {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Launch a simple OAppPrototype app
        Context context = getInstrumentation().getContext();

        Intent intent = context.getPackageManager() .getLaunchIntentForPackage(OApp_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

    }
}