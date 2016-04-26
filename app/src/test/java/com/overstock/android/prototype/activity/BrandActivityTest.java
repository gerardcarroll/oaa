package com.overstock.android.prototype.activity;

import android.os.Build;

import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.component.FeedActivityComponent;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.module.FeedActivityModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.junit.Assert.assertNotNull;

/**
 * @author LeeMeehan Created on 06-Apr-16.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class BrandActivityTest  {


  @Rule
  public final DaggerMockRule<FeedActivityComponent> mockRule1 = new DaggerMockRule<>(FeedActivityComponent.class,
          new FeedActivityModule()).addComponentDependency(ApplicationComponent.class,
          new ApplicationModule(new OAppPrototypeApplication()));

  private BrandActivity brandActivity;

  @Before
  public void setUp() {
    brandActivity = Robolectric.buildActivity(BrandActivity.class).create().start().resume().visible().get();
  }

  @After
  public void tearDown(){
    Robolectric.reset();
  }

  @Test
  public void testBrandActivity_Creation() {
    assertNotNull(brandActivity);
  }

}
