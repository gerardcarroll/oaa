package com.overstock.android.prototype.presenter;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.content.Context;
import android.content.res.Resources;

import com.overstock.android.prototype.model.Community;
import com.overstock.android.prototype.presenter.impl.CommunitiesPresenterImpl;
import com.overstock.android.prototype.view.CommunitiesView;

/**
 * @author LeeMeehan Created on 25-Mar-16.
 */
public class CommunitiesPresenterTest {

  private static String[] MOCK_IMAGE_ARRAY = { "cat_men", "cat_games" };

  private CommunitiesPresenter communitiesPresenter;

  @Mock
  private Context context;

  @Mock
  private CommunitiesView communitiesView;

  @Mock
  private Resources resources;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    communitiesPresenter = new CommunitiesPresenterImpl(context);
    communitiesPresenter.attachedView(communitiesView);
  }

  @Test
  public void testPopulateAndShowCommunities() {
    assertNotNull("CommunitiesPresenter is null. check that it has been initialized.", communitiesPresenter);
    when(context.getResources()).thenReturn(resources);
    when(resources.getStringArray(isA(Integer.class))).thenReturn(MOCK_IMAGE_ARRAY);
    communitiesPresenter.populateAndShowCommunities();
    verify(communitiesView).showCommunities(anyListOf(Community.class));
  }

  @Test(expected = NullPointerException.class)
  public void testPopulateAndShowCommunities_View_Null() {
    assertNotNull("CommunitiesPresenter is null. check that it has been initialized.", communitiesPresenter);
    communitiesPresenter.detachView();
    when(context.getResources()).thenReturn(resources);
    when(resources.getStringArray(isA(Integer.class))).thenReturn(MOCK_IMAGE_ARRAY);
    communitiesPresenter.populateAndShowCommunities();
  }

}
