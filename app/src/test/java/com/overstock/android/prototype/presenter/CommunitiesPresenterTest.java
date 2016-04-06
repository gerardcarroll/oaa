package com.overstock.android.prototype.presenter;

import android.content.Context;
import android.content.res.Resources;

import com.overstock.android.prototype.models.Community;
import com.overstock.android.prototype.service.CommunityService;
import com.overstock.android.prototype.view.CommunityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author LeeMeehan Created on 25-Mar-16.
 */
public class CommunitiesPresenterTest {

  private static String[] MOCK_IMAGE_ARRAY = { "cat_men", "cat_games" };

  private CommunityPresenter communityPresenter;

  @Mock
  private CommunityService communityService;

  @Mock
  private Context context;

  @Mock
  private CommunityView communityView;

  @Mock
  private Resources resources;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    communityPresenter = new CommunityPresenterImpl(communityService);
    communityPresenter.setView(communityView);
  }

  @Test
  public void testPopulateAndShowCommunities() {
    assertNotNull("CommunityPresenter is null, check that it has been initialized.", communityPresenter);
    when(context.getResources()).thenReturn(resources);
    when(resources.getStringArray(isA(Integer.class))).thenReturn(MOCK_IMAGE_ARRAY);
    communityPresenter.populateAndShowCommunities();
    verify(communityView).showCommunities(anyListOf(Community.class));
  }

  @Test(expected = NullPointerException.class)
  public void testPopulateAndShowCommunities_View_Null() {
    assertNotNull("CommunityPresenter is null, check that it has been initialized.", communityPresenter);
    communityPresenter.destroyView();
    when(context.getResources()).thenReturn(resources);
    when(resources.getStringArray(isA(Integer.class))).thenReturn(MOCK_IMAGE_ARRAY);
    communityPresenter.populateAndShowCommunities();
  }

}
