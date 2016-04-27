package com.overstock.android.prototype.activity;

import android.app.Application;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.ogaclejapan.arclayout.ArcLayout;
import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.OAppPrototypeApplicationMockRule;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.component.FeedActivityComponent;
import com.overstock.android.prototype.fragment.ArcMenuFragment;
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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Robolectric tests to check Arc Menu functionality.
 *
 * @author RayConnolly Created on 4/7/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, application =  com.overstock.android.prototype.TestOAppPrototypeApplication.class)
@RunWith(RobolectricGradleTestRunner.class)
public class ArcMenuFragmentTest  {

    private ArcMenuFragment arcMenuFragment;

    @Rule
    public OAppPrototypeApplicationMockRule oAppPrototypeApplicationMockRule = new OAppPrototypeApplicationMockRule();

    @Rule
    public final DaggerMockRule<FeedActivityComponent> mockRule1 = new DaggerMockRule<>(FeedActivityComponent.class,
            new FeedActivityModule()).addComponentDependency(ApplicationComponent.class,
            new ApplicationModule(mock(Application.class)));

    @Before
    public void setUp() {
        arcMenuFragment = new ArcMenuFragment();
        startFragment(arcMenuFragment, FragmentActivity.class);

    }
    @After
    public void tearDown(){
        Robolectric.reset();
    }

    @Test
    public void testFragmentNotNull(){
        assertNotNull(arcMenuFragment);
    }

    @Test
    public void testArcMenuCreation() {
        assertNotNull(arcMenuFragment);

        ImageButton fab = (ImageButton) arcMenuFragment.getView().findViewById(R.id.fab);
        assertNotNull(fab);

        FrameLayout menuLayout = (FrameLayout) arcMenuFragment.getView().findViewById(R.id.menu_layout);
        assertNotNull(menuLayout);
        assertEquals(1, menuLayout.getChildCount());

        ArcLayout arcLayout = (ArcLayout) arcMenuFragment.getView().findViewById(R.id.arc_layout);
        assertNotNull(arcLayout);
        assertEquals(3, arcLayout.getChildCount());
    }

}
