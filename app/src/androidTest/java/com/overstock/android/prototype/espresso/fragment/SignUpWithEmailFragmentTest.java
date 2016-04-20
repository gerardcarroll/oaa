package com.overstock.android.prototype.espresso.fragment;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by rconnolly on 4/20/2016.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class SignUpWithEmailFragmentTest {

    private static final String registeredUsername = "testuser1@gmail.com" ;
    private static final String newUsername = "testuser52@gmail.com" ;
    private static final String password = "androidsignuptest" ;
    private static final String passwordConfirm = "androidsignuptest2" ;

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class, true, false);

    @Before
    public void setUp() {
        activityRule.launchActivity(null);
    }

    @Test
    public void testConnectWithEmailButtonRendering() {

        // Check Connect with Email button is displayed
        onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void testSignUpScreenFlow(){

        // Check Connect with Email button is displayed on Home Activity
        onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));

        // Click Connect with Email button on Home Activity
        onView(withId(R.id.email_login_btn)).perform(click());

        // Check Sign Up link text is displayed on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).check(matches(isDisplayed()));

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).perform(click());

        // Check Sign Up button is displayed on Sign Up fragment
        onView(withId(R.id.btn_sign_up)).check(matches(isDisplayed()));
    }

    @Test
    public void testRequiredFieldsToastIsDisplayed(){

        // Check Connect with Email button is displayed on Home Activity
        onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));

        // Click Connect with Email button on Home Activity
        onView(withId(R.id.email_login_btn)).perform(click());

        // Check Sign Up link text is displayed on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).check(matches(isDisplayed()));

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).perform(click());

        // Check Sign Up button is displayed on Sign Up fragment
        onView(withId(R.id.btn_sign_up)).check(matches(isDisplayed()));

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.btn_sign_up)).perform(click());

        // Check Error message Toast is displayed on Sign Up fragment
        onView(withText(activityRule.getActivity().getResources().getString(R.string.sign_up_required_fields_message))).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testPasswordConfirmToastIsDisplayed(){

        // Check Connect with Email button is displayed on Home Activity
        onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));

        // Click Connect with Email button on Home Activity
        onView(withId(R.id.email_login_btn)).perform(click());

        // Check Sign Up link text is displayed on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).check(matches(isDisplayed()));

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).perform(click());

        // Check Sign Up button is displayed on Sign Up fragment
        onView(withId(R.id.btn_sign_up)).check(matches(isDisplayed()));

        // Enter newUsername
        onView(withId(R.id.et_username)).perform(clearText(), typeText(newUsername));

        // Enter password
        onView(withId(R.id.et_password)).perform(clearText(), typeText(password));

        // Enter password confirmation
        onView(withId(R.id.et_confirm_password)).perform(clearText(), typeText(passwordConfirm));

        // Close keyboard
        Espresso.closeSoftKeyboard();

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.btn_sign_up)).perform(click());

        // Check Error message Toast is displayed on Sign Up fragment
        onView(withText(activityRule.getActivity().getResources().getString(R.string.sign_up_passwordconfirm_error_message))).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testSignUpUnsuccessfulToastIsDisplayed(){

        // Check Connect with Email button is displayed on Home Activity
        onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));

        // Click Connect with Email button on Home Activity
        onView(withId(R.id.email_login_btn)).perform(click());

        // Check Sign Up link text is displayed on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).check(matches(isDisplayed()));

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).perform(click());

        // Check Sign Up button is displayed on Sign Up fragment
        onView(withId(R.id.btn_sign_up)).check(matches(isDisplayed()));

        // Enter registeredUsername
        onView(withId(R.id.et_username)).perform(clearText(), typeText(registeredUsername));

        // Enter password
        onView(withId(R.id.et_password)).perform(clearText(), typeText(password));

        // Enter password confirmation
        onView(withId(R.id.et_confirm_password)).perform(clearText(), typeText(password));

        // Close keyboard
        Espresso.closeSoftKeyboard();

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.btn_sign_up)).perform(click());

        SystemClock.sleep(3000L);

        // Check Success message Toast is displayed on Sign Up fragment
        onView(withText(activityRule.getActivity().getResources().getString(R.string.unsuccessful_oapp_sign_up_message))).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }

    @Test
    public void testSignUpSuccessfulToastIsDisplayed(){

        // Check Connect with Email button is displayed on Home Activity
        onView(withId(R.id.email_login_btn)).check(matches(isDisplayed()));

        // Click Connect with Email button on Home Activity
        onView(withId(R.id.email_login_btn)).perform(click());

        // Check Sign Up link text is displayed on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).check(matches(isDisplayed()));

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.txt_email_sign_up)).perform(click());

        // Check Sign Up button is displayed on Sign Up fragment
        onView(withId(R.id.btn_sign_up)).check(matches(isDisplayed()));

        // Enter newUsername
        onView(withId(R.id.et_username)).perform(clearText(), typeText(newUsername));

        // Enter password
        onView(withId(R.id.et_password)).perform(clearText(), typeText(password));

        // Enter password confirmation
        onView(withId(R.id.et_confirm_password)).perform(clearText(), typeText(password));

        // Close keyboard
        Espresso.closeSoftKeyboard();

        // Click Sign Up link text on Sign In fragment
        onView(withId(R.id.btn_sign_up)).perform(click());

        SystemClock.sleep(3000L);

        // Check Success message Toast is displayed on Sign Up fragment
        onView(withText(activityRule.getActivity().getResources().getString(R.string.successful_oapp_sign_up_message))).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

        // Check Communities recycler view is displayed
        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));

    }
}
