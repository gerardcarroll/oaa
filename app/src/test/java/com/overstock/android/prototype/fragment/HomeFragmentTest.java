package com.overstock.android.prototype.fragment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import android.os.Build;
import android.view.View;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

/**
 * @author LeeMeehan on 16-Mar-16.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class HomeFragmentTest {
  private HomeFragment homeFragment;

  @Before
  public void setUp() {
    homeFragment = new HomeFragment();
  }

  @Test
  public void testHomeFragmentStart() {
    SupportFragmentTestUtil.startFragment(homeFragment, HomeActivity.class);

    // Checking fragment has been created
    assertNotNull(homeFragment);
    assertNotNull(homeFragment.getView());
    assertNotNull(homeFragment.getActivity());
    assertThat(homeFragment.getActivity(), instanceOf(HomeActivity.class));

    // Assert elements are visible.
    View faceBookButton = homeFragment.getView().findViewById(R.id.facebook_login_btn);
    View googlePlusButton = homeFragment.getView().findViewById(R.id.googlePlus_login_btn);
    View guestLogin = homeFragment.getView().findViewById(R.id.guest_login_btn);

    assertEquals(View.VISIBLE, faceBookButton.getVisibility());
    assertEquals(View.VISIBLE, googlePlusButton.getVisibility());
    assertEquals(View.VISIBLE, guestLogin.getVisibility());
  }

}
