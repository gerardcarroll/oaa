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


        UiObject2 OAppPrototypeApp = mDevice.findObject(By.text("OAppPrototype"));
        OAppPrototypeApp.click();

        // Wait till the Login buttons are on the screen
        //mDevice.wait(Until.hasObject(By.text("Connect with G+")), 10000);

        UiObject2 ConnectWithGplus =  OAppPrototypeApp.findObject(By.res("com.overstock.android.prototype","id:googlePlus_login_btn"));
        Assert.assertNotNull(ConnectWithGplus);
        ConnectWithGplus.click();

//        UiObject ConnectWithGplus = new UiObject(new UiSelector().text("Connect with G+"));
 //       ConnectWithGplus.click();

//        mDevice.findObject(new UiSelector()
//                .packageName(OApp_PACKAGE).text("OAppPrototype")).click();

//        mDevice.findObject(new UiSelector()
//                .packageName(OApp_PACKAGE).resourceId("com.overstock.android.prototype:id/googlePlus_login_btn")).click();
//



//        // Wait for launcher
//        final String launcherPackage = mDevice.getLauncherPackageName();
//        assertThat(launcherPackage, notNullValue());
//        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
//                LAUNCH_TIMEOUT);
//
//        // Launch the app
//        Context context = InstrumentationRegistry.getContext();
//        final Intent intent = context.getPackageManager()
//                .getLaunchIntentForPackage(OApp_PACKAGE);
//
//        // Clear out any previous instances
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(intent);
//
//        // Wait for the app to appear
//        mDevice.wait(Until.hasObject(By.pkg(OApp_PACKAGE).depth(0)),
//                TIMEOUT);
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

        // Clear out any previous instances
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(OApp_PACKAGE).depth(0)), TIMEOUT);
    }


    }

