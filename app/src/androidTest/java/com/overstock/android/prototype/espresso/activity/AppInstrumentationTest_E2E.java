package com.overstock.android.prototype.espresso.activity;

//import org.mockito.Mock;

/**
 * Created by itowey on 14/03/16.
 */
//@RunWith(AndroidJUnit4.class)
public class AppInstrumentationTest_E2E {

//    public static final String USERNAME = "johnsmith@gmail.com";
//
//    @Rule public ActivityTestRule<HomeActivity> activityRule = new ActivityTestRule<>(HomeActivity.class);
//
//    @Rule public OAppPrototypeApplicationMockRule oAppPrototypeApplicationMockRule = new OAppPrototypeApplicationMockRule();
//
//    @Mock OappGoogleAuthService oappGoogleAuthService;
//
//    @Before
//    public void setUp(){
//
//        when(oappGoogleAuthService.silentSignInStatus()).thenReturn(new OptionalPendingResult<GoogleSignInResult>() {
//            @Override public boolean isDone() {
//                return true;
//            }
//            @Override public GoogleSignInResult get() {
//                GoogleSignInResult mockGoogleSignInResult = mock(GoogleSignInResult.class);
//                when(mockGoogleSignInResult.getStatus()).thenReturn(Status.zzagC);
//                when(mockGoogleSignInResult.isSuccess()).thenReturn(true);
//                GoogleSignInAccount mockGoogleSignInAccount = mock(GoogleSignInAccount.class);
//                when(mockGoogleSignInAccount.getDisplayName()).thenReturn(USERNAME);
//                when(mockGoogleSignInResult.getSignInAccount()).thenReturn(mockGoogleSignInAccount);
//
//                return mockGoogleSignInResult;
//            }
//            @NonNull
//            @Override public GoogleSignInResult await() {
//                return null;
//            }
//            @NonNull @Override public GoogleSignInResult await(long l, TimeUnit timeUnit) { return null; }
//            @Override public void cancel() {            }
//            @Override public boolean isCanceled() {
//                return false;
//            }
//            @Override public void setResultCallback(ResultCallback<? super GoogleSignInResult> resultCallback) {}
//            @Override public void setResultCallback(ResultCallback<? super GoogleSignInResult> resultCallback, long l, TimeUnit timeUnit) {
//            }
//        });
//    }
//
//    @Test
//    public void appTest(){
//        onView(withId(R.id.guest_login_btn)).perform(click());
//        onView(withId(R.id.rvCommunities)).check(matches(isDisplayed()));
//        onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
//        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//        onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
//        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
//        onView(withId(R.id.btnCommunitySelection)).check(matches(not(isEnabled())));
//        onView(withId(R.id.rvCommunities)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
//        onView(withId(R.id.btnCommunitySelection)).check(matches(isEnabled()));
//        onView(withId(R.id.btnCommunitySelection)).perform(click());
//        onView(withId(R.id.feed_tabs)).check(matches(isDisplayed()));
//        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());
//        onView(withId(R.id.feed_viewpager)).check(matches(isDisplayed()));
//        onView(withText(activityRule.getActivity().getString(R.string.trending_tab))).perform(click());
//        onView(withText("Trending Fragment")).check(matches(isDisplayed()));
//        onView(withId(R.id.feed_viewpager)).check(matches(isDisplayed()));
//        onView(withText(activityRule.getActivity().getString(R.string.my_location_tab))).perform(click());
//        onView(withText("My Location Fragment")).check(matches(isDisplayed()));
//
//        onView(withText(activityRule.getActivity().getString(R.string.my_feed_tab))).perform(click());
//        onView(withText("Top NFL Fan Products for 2016")).check(matches(isDisplayed()));
//        onView(withId(R.id.rv_feed_communities)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//        onView(withId(R.id.brand_activity)).check(matches(isDisplayed()));
//
////        onView(isRoot()).perform(waitId(R.id.best_sellers, Sampling.SECONDS_15));
////        onView(withId(R.id.best_sellers)).perform(RecyclerViewActions.scrollToPosition(10));
////        onView(withId(R.id.best_sellers)).perform(RecyclerViewActions.scrollToPosition(1));
////        onView(withId(R.id.best_sellers)).perform(RecyclerViewActions.scrollToPosition(5)).perform(click());
////        onView(withId(R.id.shared_activity_image_1)).check(matches(isDisplayed()));
//
//    }
}
