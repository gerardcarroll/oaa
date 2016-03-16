package com.overstock.android.prototype.activity;

import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;

import com.overstock.android.prototype.BuildConfig;

/**
 * Simple Test class to test that the Home Activity was created successfully
 *
 * @author LeeMeehan Created on 16-Mar-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeActivityTest {

    private HomeActivity homeActivity;

    @Before
    public void setUp() {
        //BuildActivity creates a controller that can be used to drive through the app lifecycle.
        homeActivity = Robolectric.buildActivity(HomeActivity.class).get();
    }

    @Test
    public void testHomeActivityCreated() {
        assertNotNull(homeActivity);
    }

}
