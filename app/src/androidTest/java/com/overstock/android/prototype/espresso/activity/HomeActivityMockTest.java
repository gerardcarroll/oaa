package com.overstock.android.prototype.espresso.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;
import android.widget.Toast;

import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.espresso.dagger.rules.HomeActivityFragmentsModuleMockRule;
import com.overstock.android.prototype.espresso.matcher.CustomMatcher;
import com.overstock.android.prototype.fragment.HomeFragment;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.doAnswer;

//import org.mockito.Mock;

/**
 * Created by itowey on 14/03/16.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityMockTest {

    private static final String MOCK_GUEST_MSG = "Mock Guest Message!";

    @Rule public HomeActivityFragmentsModuleMockRule homeActivityMock = new HomeActivityFragmentsModuleMockRule();

    @Rule
    public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);

    @Mock HomeFragment homeFragment;

    //@Test
    public void mockGuestClick(){
        Assert.assertNotNull(homeFragment);

        activityRule.getActivity().setHomeFragment(homeFragment);

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                final Toast toast = Toast.makeText(activityRule.getActivity(), MOCK_GUEST_MSG, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
                return null;
            }
        }).when(homeFragment).guestLogin_onClick();
        onView(withId(R.id.guest_login_btn)).perform(click());
        onView(withText(MOCK_GUEST_MSG)).inRoot(CustomMatcher.isToast()).check(matches(isDisplayed()));
    }

}
