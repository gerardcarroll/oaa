package com.overstock.android.prototype.espresso.activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import android.support.annotation.NonNull;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.espresso.dagger.rules.OAppPrototypeApplicationMockRule;
import com.overstock.android.prototype.service1.OappGoogleAuthService;

/**
 * @author itowey Created on 14/03/16.
 */
@RunWith(AndroidJUnit4.class)
public class AppInstrumentationTest_E2E {

  public static final String USERNAME = "johnsmith@gmail.com";

  @Rule
  public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

  @Rule
  public OAppPrototypeApplicationMockRule oAppPrototypeApplicationMockRule = new OAppPrototypeApplicationMockRule();

  @Mock
  OappGoogleAuthService oappGoogleAuthService;

  @Before
  public void setUp() {

    when(oappGoogleAuthService.silentSignInStatus()).thenReturn(new OptionalPendingResult<GoogleSignInResult>() {
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
        when(mockGoogleSignInAccount.getDisplayName()).thenReturn(USERNAME);
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
    });
  }

  @Test
  public void appTest() {

    onView(withId(R.id.googlePlus_login_btn)).perform(click());
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
    onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
    onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    onView(withId(R.id.btnCommunitySelection)).check(matches(isEnabled()));
    onView(withId(R.id.btnCommunitySelection)).perform(click());
    onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
    onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());
    onView(withId(R.id.feed_viewpager)).check(matches(isDisplayed()));
    onView(withText(activityRule.getActivity().getString(R.string.trending_tab))).perform(click());
    onView(withText("Trending Fragment")).check(matches(isDisplayed()));
    onView(withId(R.id.feed_viewpager)).check(matches(isDisplayed()));
    onView(withText(activityRule.getActivity().getString(R.string.my_location_tab))).perform(click());
    onView(withText("My Location Fragment")).check(matches(isDisplayed()));

    onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());
    onView(withText("Top NFL Fan Products for 2016")).check(matches(isDisplayed()));
    onView(withId(R.id.rv_feed)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    onView(withId(R.id.brand_activity)).check(matches(isDisplayed()));

    /* Back stack Navigation */
    pressBack();
    onView(withText("Top NFL Fan Products for 2016")).check(matches(isDisplayed()));
    onView(withId(R.id.feed_viewpager)).check(matches(isDisplayed()));
    onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
    pressBack();
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
    onView(withId(R.id.btnCommunitySelection)).check(matches(isEnabled())); // Asserting Community Selections have not
                                                                            // been lost.
    pressBack();
    onView(withId(R.id.guest_login_btn)).check(doesNotExist()); // Assert that we have not rooted to the login page.
    onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));// Assert that we have not rooted from the
                                                                     // Communities Page.
  }
}
