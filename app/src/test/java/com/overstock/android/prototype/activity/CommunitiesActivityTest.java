package com.overstock.android.prototype.activity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;

import com.dd.processbutton.iml.SubmitProcessButton;
import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;

/**
 * @author LeeMeehan Created on 25-Mar-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class CommunitiesActivityTest {

  private static final int NUMBER_OF_COMMUNITIES_EXPECTED = 18;

  private static final int EXPECTED_PERCENTAGE_ONE_ITEM_SELECTED = 34;

  private static final int EXPECTED_PERCENTAGE_TWO_ITEMS_SELECTED = 67;

  private static final int EXPECTED_PERCENTAGE_THREE_ITEMS_SELECTED = 100;

  private CommunitiesActivity communitiesActivity;

  private SubmitProcessButton processButton;

  private RecyclerView communitiesView;

  @Before
  public void setUp() {
    communitiesActivity = Robolectric.buildActivity(CommunitiesActivity.class).create().start().resume().visible()
        .get();
    processButton = (SubmitProcessButton) communitiesActivity.findViewById(R.id.btnCommunitySelection);
    communitiesView = (RecyclerView) communitiesActivity.findViewById(R.id.rvCommunities);
  }

  /**
   * Simple Test chase to test that the Communities Activity was created successfully and the communities grid has been
   * populated.
   */
  @Test
  public void testCommunitiesActivity_Creation() {
    assertNotNull("CommunitiesActivity is null it was not Created.", communitiesActivity);
    assertNotNull("ProcessButton dose not exist received null.", processButton);
    assertEquals("Progress is not at 0 when activity is first created.", 0, processButton.getProgress());
    assertNotNull("The communitiesView is null when the activity is first created.", communitiesView);
    assertEquals("The adapter did not receive the expected number of communities.", NUMBER_OF_COMMUNITIES_EXPECTED,
      communitiesView.getAdapter().getItemCount());
  }

  /**
   * This test asserts that when items on the community grid are selected the progress button response accordingly. It
   * then asserts that once the progress button reaches 100% and is clicked that the FeedActivity is started.
   */
  @Test
  public void testCommunitiesActivity_HappyPath() {
    assertFalse("ProcessButton is enabled. Expected to be Disabled", processButton.isEnabled());
    communitiesView.getChildAt(0).performClick();
    assertEquals("The percentage after one item has been select is not as expected.",
      EXPECTED_PERCENTAGE_ONE_ITEM_SELECTED, processButton.getProgress());
    assertFalse("ProcessButton is enabled. Expected to be Disabled", processButton.isEnabled());
    communitiesView.getChildAt(1).performClick();
    assertEquals("The percentage after two items has been select is not as expected.",
      EXPECTED_PERCENTAGE_TWO_ITEMS_SELECTED, processButton.getProgress());
    assertFalse("ProcessButton is enabled. Expected to be Disabled", processButton.isEnabled());
    communitiesView.getChildAt(1).performClick();
    assertEquals("The percentage after deselecting a item is not as expected.", EXPECTED_PERCENTAGE_ONE_ITEM_SELECTED,
      processButton.getProgress());
    assertFalse("ProcessButton is enabled. Expected to be Disabled", processButton.isEnabled());
    communitiesView.getChildAt(1).performClick();
    communitiesView.getChildAt(2).performClick();
    assertEquals("The percentage after three items has been select is not as expected.",
      EXPECTED_PERCENTAGE_THREE_ITEMS_SELECTED, processButton.getProgress());
    assertTrue("ProcessButton is Disabled. Expected to be Enabled", processButton.isEnabled());
    processButton.performClick();
    ShadowActivity shadowActivity = Shadows.shadowOf(communitiesActivity);
    Intent startedIntent = shadowActivity.getNextStartedActivity();
    assertNotNull("The started intent is null. No Activity has started.", startedIntent);
    assertNotNull("The intent is empty.", startedIntent.getComponent());
    assertThat("The started Activity is not the activity that is expected", startedIntent.getComponent().getClassName(),
      equalTo(FeedActivity.class.getName()));
  }

}
