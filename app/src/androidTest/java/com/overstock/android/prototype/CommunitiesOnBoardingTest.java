package com.overstock.android.prototype;

import android.content.Context;
import android.content.Intent;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by finolacurran on 3/8/2016.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class CommunitiesOnBoardingTest {

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
        UiObject2 AppsIcon = mDevice.findObject(By.text("Apps"));
        AppsIcon.click();

        // Click the OApp icon
        //UiObject OAppPrototypeApp = mDevice.findObject(new UiSelector().description("O.com App Prototype"));

        UiObject2 OAppPrototypeApp = mDevice.findObject(By.text("O.com App Prototype"));
        OAppPrototypeApp.click();

        //Wait until the G+ Login buttons are on the screen
        mDevice.wait(Until.hasObject(By.res("com.overstock.android.prototype", "googlePlus_login_btn")), 4000);

        // Click Connect with Guest
        UiObject2 ConnectWithGplus = mDevice.findObject(By.res("com.overstock.android.prototype", "guest_login_btn"));
        ConnectWithGplus.click();

        //Wait until the recyclerview is on the screen.  It has TextView Men.
        mDevice.wait(Until.hasObject(By.text("Men")), 4000);

        // click community men
        UiObject2 CommunityMen = mDevice.findObject(By.text("Men"));
        CommunityMen.click();

        // click community Women
        UiObject2 CommunityWomen = mDevice.findObject(By.text("Women"));
        CommunityWomen.click();

        // click community Home Decor
        UiObject2 CommunityHomeDecor = mDevice.findObject(By.text("Home Decor"));
        CommunityHomeDecor.click();
        // wait until the Continue button is there
        mDevice.wait(Until.hasObject(By.text("Continue")), 3000);

        // click continue button
        UiObject2 ContinueButton = mDevice.findObject(By.text("Continue ✓"));
        //UiObject2 ContinueButton = mDevice.findObject(By.res("com.overstock.android.prototype","btn_community_continue"));
        ContinueButton.click();

        //wait until feed image is on the page
        UiObject2 MyFeedPage = mDevice.findObject(By.res("com.overstock.android.prototype","feed_img"));
        mDevice.wait(Until.hasObject(By.res("com.overstock.android.prototype","feed_img")), 3000);

        //Click on Trending tab on feed page
        UiObject2 TrendingTab = mDevice.findObject(By.text("Trending"));
        TrendingTab.click();

        /* Click on My Location tab on feed page */
        UiObject2 MyLocationTab = mDevice.findObject(By.text("My Location"));
        TrendingTab.click();

        //Click on My Feed tab on feed page
        UiObject2 MyFeedTab = mDevice.findObject(By.text("My Feed"));
        MyFeedTab.click();

        //Click on feed page
        UiObject2 FeedPage = mDevice.findObject(By.res("com.overstock.android.prototype","feed_img"));
        FeedPage.click();

        //scroll on the my feed page and then click on it
        //UiScrollable FeedPageScroll = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        //UiScrollable FeedPageScroll = new UiScrollable(new UiSelector().className("android.support.v7.widget.RecyclerView"));
        //FeedPageScroll.scrollForward();
        //MyFeedPage = mDevice.findObject(By.res("com.overstock.android.prototype","feed_img"));
        //MyFeedPage.click();

        //scrollForward();

        //Assert that user is on Brand page and Click on header on Brand page
        UiObject2 BrandPageHeader = mDevice.findObject(By.res("com.overstock.android.prototype","header"));
        //Assert.assertNotNull(BrandPageHeader);
        BrandPageHeader.click();

        //Click a product image on Brand page
        UiObject2 BrandProduct = mDevice.findObject(By.res("com.overstock.android.prototype","product_card_img"));
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