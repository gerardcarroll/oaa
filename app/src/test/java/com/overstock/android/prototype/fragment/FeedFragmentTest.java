package com.overstock.android.prototype.fragment;

import android.os.Build;
import android.support.v7.widget.RecyclerView;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Feed;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.presenter.FeedPresenter;
import com.squareup.picasso.Picasso;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * @author LeeMeehan Created on 05-Apr-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class FeedFragmentTest {

  @Rule
  public final DaggerMockRule<ApplicationComponent> mockRule = new DaggerMockRule<>(ApplicationComponent.class,
      new ApplicationModule(new OAppPrototypeApplication()))
          .set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
            @Override
            public void setComponent(ApplicationComponent applicationComponent) {
              ((OAppPrototypeApplication) RuntimeEnvironment.application).setComponent(applicationComponent);
            }
          });

  private static final int ITEM_COUNT = 1;

  private static final int PRODUCT_IMAGE = 1001;

  private static final String TOP_PRODUCTS_LINK = "TEST_SHOP";

  private static final String PRODUCT_URL = "TEST.com";

  private FeedFragment feedFragment;

  @Mock
  private Picasso picasso;

  @Mock
  FeedPresenter feedPresenter;

  @Before
  public void setUp() {
    feedFragment = new FeedFragment();
    startFragment(feedFragment);
  }

  @After
  public void tearDown(){
    Robolectric.reset();
  }

  @Test
  public void testFragmentNotNull() {
    assertNotNull(feedFragment);
  }

  @Test
  public void testUpdateFeed() {
    ArrayList<Feed> feeds = new ArrayList<>();
    feeds.add(new Feed(PRODUCT_IMAGE, TOP_PRODUCTS_LINK, PRODUCT_URL));
    feedFragment.updateFeed(feeds);
    RecyclerView recyclerView = (RecyclerView) feedFragment.getView().findViewById(R.id.rv_feed);
    assertNotNull(recyclerView);
    assertNotNull(recyclerView.getAdapter());
    assertEquals(ITEM_COUNT, recyclerView.getAdapter().getItemCount());
  }
}
