package com.overstock.android.prototype.espresso.activity;

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
import com.overstock.android.prototype.service.OappGoogleAuthService;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import org.mockito.Mock;

/**
 * Created by itowey on 14/03/16.
 */
@RunWith(AndroidJUnit4.class)
public class AppInstrumentationTest_E2E {

    public static final String USERNAME = "johnsmith@gmail.com";

    @Rule public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Rule public OAppPrototypeApplicationMockRule oAppPrototypeApplicationMockRule = new OAppPrototypeApplicationMockRule();

    @Mock OappGoogleAuthService oappGoogleAuthService;

    @Before
    public void setUp(){

        when(oappGoogleAuthService.silentSignInStatus()).thenReturn(new OptionalPendingResult<GoogleSignInResult>() {
            @Override public boolean isDone() {
                return true;
            }
            @Override public GoogleSignInResult get() {
                GoogleSignInResult mockGoogleSignInResult = mock(GoogleSignInResult.class);
                when(mockGoogleSignInResult.getStatus()).thenReturn(Status.zzagC);
                when(mockGoogleSignInResult.isSuccess()).thenReturn(true);
                GoogleSignInAccount mockGoogleSignInAccount = mock(GoogleSignInAccount.class);
                when(mockGoogleSignInAccount.getDisplayName()).thenReturn(USERNAME);
                when(mockGoogleSignInResult.getSignInAccount()).thenReturn(mockGoogleSignInAccount);

                return mockGoogleSignInResult;
            }
            @NonNull
            @Override public GoogleSignInResult await() {
                return null;
            }
            @NonNull @Override public GoogleSignInResult await(long l, TimeUnit timeUnit) { return null; }
            @Override public void cancel() {            }
            @Override public boolean isCanceled() {
                return false;
            }
            @Override public void setResultCallback(ResultCallback<? super GoogleSignInResult> resultCallback) {}
            @Override public void setResultCallback(ResultCallback<? super GoogleSignInResult> resultCallback, long l, TimeUnit timeUnit) {
            }
        });
    }

    @Test
    public void alreadySignedInWithGPlus(){

        onView(withId(R.id.googlePlus_login_btn)).perform(click());
        onView(withId(R.id.your_interests_title)).check(matches(isDisplayed()));
        onView(withId(R.id.your_interests_communities_btn)).perform(click());
        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
        onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.btnCommunitySelection)).check(matches(isEnabled()));
        onView(withId(R.id.btnCommunitySelection)).perform(click());
        onView(withId(R.id.your_interests_nfl_btn)).check(matches(isDisplayed()));
        onView(withId(R.id.your_interests_nfl_btn)).perform(click());
        onView(withId(R.id.brand_toolbar)).check(matches(isDisplayed()));

    }
}
