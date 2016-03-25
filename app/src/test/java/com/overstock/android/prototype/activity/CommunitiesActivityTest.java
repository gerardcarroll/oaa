package com.overstock.android.prototype.activity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowViewGroup;

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

  private CommunitiesActivity communitiesActivity;

  private SubmitProcessButton processButton;

  private RecyclerView communitiesView;

  @Before
  public void setUp() {
    communitiesActivity = Robolectric.buildActivity(CommunitiesActivity.class).create().start().resume().visible().get();
    processButton = (SubmitProcessButton) communitiesActivity
            .findViewById(R.id.btnCommunitySelection);
    communitiesView = (RecyclerView)communitiesActivity.findViewById(R.id.rvCommunities);
  }

  @Test
  public void testCommunitiesActivity_Creation() {
    assertNotNull("CommunitiesActivity is null it was not Created.", communitiesActivity);
    assertNotNull("ProcessButton dose not exist received null.", processButton);
    assertEquals("Progress is not at 0 when activity is first created.", 0, processButton.getProgress());
    assertNotNull("The communitiesView is null when the activity is first created.", communitiesView);

    System.out.println(communitiesView.getAdapter().getItemCount());
    assertNotNull(communitiesView.getAdapter().getItemCount());
  }

}
