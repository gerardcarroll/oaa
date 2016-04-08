package com.overstock.android.prototype.activity;

import android.os.Build;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.ogaclejapan.arclayout.ArcLayout;
import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.fragment.ArcMenuFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Robolectric tests to check Arc Menu functionality.
 *
 * @author RayConnolly Created on 4/7/2016.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class ArcMenuFragmentTest {

    private ArcMenuFragment arcMenuFragment;

    @Before
    public void setUp() {
        arcMenuFragment = new ArcMenuFragment();
        startFragment(arcMenuFragment);
    }

    @Test
    public void testFragmentNotNull(){
        assertNotNull(arcMenuFragment);
    }

    @Test
    public void testArcMenuCreation() {
        assertNotNull(arcMenuFragment);

        ImageButton fab = (ImageButton) arcMenuFragment.getActivity().findViewById(R.id.fab);
        assertNotNull(fab);

        FrameLayout menuLayout = (FrameLayout) arcMenuFragment.getActivity().findViewById(R.id.menu_layout);
        assertNotNull(menuLayout);
        assertEquals(1, menuLayout.getChildCount());

        ArcLayout arcLayout = (ArcLayout) arcMenuFragment.getActivity().findViewById(R.id.arc_layout);
        assertNotNull(arcLayout);
        assertEquals(3, menuLayout.getChildCount());
    }

}
