package com.overstock.android.prototype.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.service.OappGoogleAuthService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowToast;

import java.util.concurrent.TimeUnit;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Simple Test class to test that the Home Activity was created successfully
 *
 * @author LeeMeehan Created on 16-Mar-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class HomeActivityTest {

  private static final String TEST_USERNAME = "Test User";

  private static final OptionalPendingResult<GoogleSignInResult> SIGN_IN_RESULT_OPTIONAL_PENDING_RESULT = new OptionalPendingResult<GoogleSignInResult>() {
    @Override
    public boolean isDone() {
      return true;
    }

    @Override
    public GoogleSignInResult get() {
      GoogleSignInResult mockGoogleSignInResult = mock(GoogleSignInResult.class);
      when(mockGoogleSignInResult.getStatus()).thenReturn(Status.zzagC);
      when(mockGoogleSignInResult.isSuccess()).thenReturn(true);
      GoogleSignInAccount mockGoogleSignInAccount = mock(GoogleSignInAccount.class);
      when(mockGoogleSignInAccount.getDisplayName()).thenReturn(TEST_USERNAME);
      when(mockGoogleSignInResult.getSignInAccount()).thenReturn(mockGoogleSignInAccount);

      return mockGoogleSignInResult;
    }

    @NonNull
    @Override
    public GoogleSignInResult await() {
      return null;
    }

    @NonNull
    @Override
    public GoogleSignInResult await(long l, TimeUnit timeUnit) {
      return null;
    }

    @Override
    public void cancel() {}

    @Override
    public boolean isCanceled() {
      return false;
    }

    @Override
    public void setResultCallback(ResultCallback<? super GoogleSignInResult> resultCallback) {}

    @Override
    public void setResultCallback(ResultCallback<? super GoogleSignInResult> resultCallback, long l,
      TimeUnit timeUnit) {}
  };

  @Rule
  public final DaggerMockRule<ApplicationComponent> mockRule = new DaggerMockRule<>(ApplicationComponent.class,
      new ApplicationModule(new OAppPrototypeApplication()))
          .set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
            @Override
            public void setComponent(ApplicationComponent applicationComponent) {
              ((OAppPrototypeApplication) RuntimeEnvironment.application).setComponent(applicationComponent);
            }
          });

  @Mock
  private OappGoogleAuthService oappGoogleAuthService;

  private HomeActivity homeActivity;

  private HomeFragment homeFragment;

  @Before
  public void setUp() {
    when(oappGoogleAuthService.signIn()).thenReturn(null);
    when(oappGoogleAuthService.silentSignInStatus()).thenReturn(SIGN_IN_RESULT_OPTIONAL_PENDING_RESULT);
    // BuildActivity creates a controller that can be used to drive through the app lifecycle.
    homeActivity = Robolectric.buildActivity(HomeActivity.class).create().start().resume().visible().get();
    homeFragment = (HomeFragment) homeActivity.getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);

  }

  @After
  public void tearDown(){
    Robolectric.reset();
  }

  @Test
  public void testHomeActivityCreated() {
    assertNotNull("Home Activity is null.", homeActivity);
    assertNotNull("Home fragment dose not exist.", homeFragment);

    // Assert elements are visible.
    Button faceBookButton = (Button) homeFragment.getView().findViewById(R.id.facebook_login_btn);
    Button googlePlusButton = (Button) homeFragment.getView().findViewById(R.id.googlePlus_login_btn);
    Button guestLogin = (Button) homeFragment.getView().findViewById(R.id.guest_login_btn);

    assertNotNull(faceBookButton);
    assertNotNull(googlePlusButton);
    assertNotNull(guestLogin);
  }

  @Test
  public void testGooglePlusLoginButton_CLICKED() {
    Button googlePlusButton = (Button) homeFragment.getView().findViewById(R.id.googlePlus_login_btn);
    googlePlusButton.performClick();

    GoogleFederatedIdentityFragment googleFederatedIdentityFragment = (GoogleFederatedIdentityFragment) homeActivity
        .getSupportFragmentManager().findFragmentByTag(GoogleFederatedIdentityFragment.TAG);
    assertNotNull("The GoogleFederatedIdentityFragment was not committed to the page.",
      googleFederatedIdentityFragment);

    ShadowActivity shadowActivity = Shadows.shadowOf(homeActivity);
    // peekNextStartedActivity dose not consume intent.
    Intent startedIntent = shadowActivity.peekNextStartedActivity();
    assertNotNull("The started intent is null. No Activity has started.", startedIntent);
    assertNotNull("The intent is a empty.", startedIntent.getComponent());
    assertThat("The started Activity is not the activity that is expected", startedIntent.getComponent().getClassName(),
      equalTo(CommunityActivity.class.getName()));
  }

  @Test
  public void testFaceBookLoginButton_CLICKED() {
    Button faceBookButton = (Button) homeFragment.getView().findViewById(R.id.facebook_login_btn);
    assertNotNull(faceBookButton);
    faceBookButton.performClick();
    assertEquals("FaceBook Login Coming Soon!", ShadowToast.getTextOfLatestToast());
  }

  @Test
  public void testGuestLoginButton_CLICKED() {
    Button guestLogin = (Button) homeFragment.getView().findViewById(R.id.guest_login_btn);
    assertNotNull(guestLogin);
    guestLogin.performClick();
    ShadowActivity shadowActivity = Shadows.shadowOf(homeActivity);
    // peekNextStartedActivity dose not consume intent.
    Intent startedIntent = shadowActivity.peekNextStartedActivity();
    assertNotNull("The started intent is null. No Activity has started.", startedIntent);
    assertNotNull("The intent is empty.", startedIntent.getComponent());
    assertThat("The started Activity is not the activity that is expected", startedIntent.getComponent().getClassName(),
      equalTo(CommunityActivity.class.getName()));
  }

}
