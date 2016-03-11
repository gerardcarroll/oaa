package com.overstock.android.prototype;


import android.content.Context;
import android.content.Intent;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Assert;
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

        // Click the OApp icon
        UiObject2 OAppPrototypeApp = mDevice.findObject(By.text("OAppPrototype"));
        OAppPrototypeApp.click();

        //Wait until the G+ Login buttons are on the screen
        mDevice.wait(Until.hasObject(By.text("Connect with G+")), 4000);

        // Click Connect with G+
        UiObject2 ConnectWithGplus = mDevice.findObject(By.text("Connect with G+"));
        ConnectWithGplus.click();

        //Wait until the Communities Link are on the screen on Your Interests Page
        mDevice.wait(Until.hasObject(By.text("Communities Link")), 4000);

        // Click Communities Link
        UiObject2 CommunitiesLink = mDevice.findObject(By.text("Communities Link"));
        Assert.assertNotNull(CommunitiesLink);
        CommunitiesLink.click();

        //Wait until the recyclerview is on the screen.  It has TextView Men.
        mDevice.wait(Until.hasObject(By.text("Men")), 4000);

        // click community women
        UiObject2 CommunityMen = mDevice.findObject(By.text("Men"));
        CommunityMen.click();

        // click community
        UiObject2 CommunityWomen = mDevice.findObject(By.text("Women"));
        CommunityWomen.click();

        // click community
        UiObject2 CommunityHomeDecor = mDevice.findObject(By.text("Home Decor"));
        CommunityHomeDecor.click();
        // wait until the Continue button is there
        mDevice.wait(Until.hasObject(By.text("Continue")), 3000);

        // click continue button
        UiObject2 PressContinue = mDevice.findObject(By.text("Continue"));
        PressContinue.click();

        // wait on the onboarding communities page
        mDevice.wait(Until.hasObject(By.res("com.overstock.android.prototype", "btnCommunities")), 3000);

        CommunitiesLink = mDevice.findObject(By.text("Communities Link"));

        //Wait until the Communities Link are on the screen on Your Interests Page
        mDevice.wait(Until.hasObject(By.text("Communities Link")), 3000);


    }

    @Before
    public void setUp() {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Launch a simple OAppPrototype app
        Context context = getInstrumentation().getContext();
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(OApp_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

       }

    }

