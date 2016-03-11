package com.overstock.android.prototype;

/**
 * Created by itowey on 10/03/16.
 */

import android.content.Context;
import android.content.Intent;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Created by finolacurran on 3/8/2016.
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 19)
public class CommunitiesOnBoardingTest {

    private static final String OApp_PACKAGE = "com.overstock.android.prototype";
    private static final String OApp_Name = "OAppPrototype";

    private UiDevice mDevice;

    private static final int TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";

    @Test
    public void startMainActivityFromHomeScreen() throws UiObjectNotFoundException {

        // Start from the home screen
        mDevice.pressHome();
        mDevice.findObject(new UiSelector().text("Apps")).clickAndWaitForNewWindow();
        mDevice.swipe(100,100,300,100,1);
        mDevice.findObject(new UiSelector().text(OApp_Name)).clickAndWaitForNewWindow();
//        mDevice.waitForWindowUpdate(OApp_PACKAGE, TIMEOUT);
        mDevice.wait(Until.hasObject(By.text("Continue As Guest").depth(2)), TIMEOUT);
        mDevice.findObject(new UiSelector().text("Continue As Guest")).clickAndWaitForNewWindow();


//
//        Assert.assertNotNull(oAppPrototypeApp);
//        Assert.assertTrue(oAppPrototypeApp.click());
//        oAppPrototypeApp.click();

        // Wait till the Login buttons are on the screen
        //mDevice.wait(Until.hasObject(By.text("Connect with G+")), 10000);

//        UiObject2 connectWithGplus = oAppPrototypeApp.findObject(By.res("com.overstock.android.prototype", "id:googlePlus_login_btn"));
//        Assert.assertNotNull(connectWithGplus);
//        connectWithGplus.click();

    }

    @Before
    public void setUp() {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Launch a simple OAppPrototype app
        Context context = getInstrumentation().getContext();
        Intent intent = context.getPackageManager() .getLaunchIntentForPackage(OApp_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Clear out any previous instances
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(OApp_PACKAGE).depth(0)), TIMEOUT);
    }
}