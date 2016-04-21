package com.overstock.android.prototype.presenter;

import com.overstock.android.prototype.RxSchedulersOverrideRule;
import com.overstock.android.prototype.model.Feed;
import com.overstock.android.prototype.presenter.impl.FeedPresenterImpl;
import com.overstock.android.prototype.service.FeedService;
import com.overstock.android.prototype.view.FeedView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author LeeMeehan Created on 06-Apr-16.
 */
@RunWith(RobolectricTestRunner.class)
public class FeedPresenterTest {

  private static final int PRODUCT_IMAGE = 1001;

  private static final String TOP_PRODUCTS_LINK = "TEST";

  private static final String PRODUCT_URL = "TEST.com";

  private FeedPresenter feedPresenter;

  @Rule
  public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();

  @Mock
  private FeedService feedService;

  @Mock
  private FeedView feedView;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    feedPresenter = new FeedPresenterImpl(feedService);
  }

  @Test
  public void testRefreshFeed() {
    final List<Feed> feeds = new ArrayList<>();
    feeds.add(new Feed(PRODUCT_IMAGE, TOP_PRODUCTS_LINK, PRODUCT_URL));
    when(feedService.getFeed()).thenReturn(Observable.just(feeds));
    feedPresenter.setView(feedView);
    verify(feedView).updateFeed((ArrayList) feeds);
  }

  @Test
  public void testSetView_Null_PARAM() {
    feedPresenter.setView(null);
    verifyZeroInteractions(feedView);
  }

  @Test(expected = NullPointerException.class)
  public void testRefreshFeed_FeedViewNull() {
    feedPresenter.onDestroy();
    feedPresenter.refreshFeed();
  }

  @Test
  public void testRefreshFeed_ERROR_RETURNED() {
    //Throwing back Observable error. To test onError.
    when(feedService.getFeed()).thenReturn(Observable.<List<Feed>>error(new RuntimeException("Test Exception")));
    feedPresenter.setView(feedView);
    verifyZeroInteractions(feedView);
  }



}
