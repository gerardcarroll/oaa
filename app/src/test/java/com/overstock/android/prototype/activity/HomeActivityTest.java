package com.overstock.android.prototype.activity;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.HomeFragment;

/**
 * Simple Test class to test that the Home Activity was created successfully
 *
 * @author LeeMeehan Created on 16-Mar-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeActivityTest {

  private HomeActivity homeActivity;

  private HomeFragment homeFragment;

  @Before
  public void setUp() {
    // BuildActivity creates a controller that can be used to drive through the app lifecycle.
    homeActivity = Robolectric.buildActivity(HomeActivity.class).create().start().resume().visible().get();
    homeFragment = (HomeFragment) homeActivity.getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
  }

  @Test
  public void testHomeActivityCreated() {
    assertNotNull("Home Activity is null.", homeActivity);
    assertNotNull("Home fragment dose not exist.", homeFragment);

    // Assert elements are visible.
    Button faceBookButton = (Button) homeFragment.getView().findViewById(R.id.facebook_login_btn);
    Button googlePlusButton = (Button) homeFragment.getView().findViewById(R.id.googlePlus_login_btn);
    Button guestLogin = (Button) homeFragment.getView().findViewById(R.id.guest_login_btn);

    assertEquals(View.VISIBLE, faceBookButton.getVisibility());
    assertEquals(View.VISIBLE, googlePlusButton.getVisibility());
    assertEquals(View.VISIBLE, guestLogin.getVisibility());
  }

  @Test
  public void testGooglePlusLoginButton_CLICKED() {
    Button googlePlusButton = (Button) homeFragment.getView().findViewById(R.id.googlePlus_login_btn);
   // googlePlusButton.performClick();
//    GoogleFederatedIdentityFragment googleFederatedIdentityFragment = (GoogleFederatedIdentityFragment) homeActivity
//        .getSupportFragmentManager().findFragmentByTag(GoogleFederatedIdentityFragment.TAG);
//    assertNotNull(googleFederatedIdentityFragment);
  }

  @Test
  public void testFaceBookLoginButton_CLICKED() {
    Button faceBookButton = (Button) homeFragment.getView().findViewById(R.id.facebook_login_btn);
    faceBookButton.performClick();
  }

  @Test
  public void testGuestLoginButton_CLICKED() {
    Button guestLogin = (Button) homeFragment.getView().findViewById(R.id.guest_login_btn);
    guestLogin.performClick();
  }


}
