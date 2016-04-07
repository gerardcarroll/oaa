package com.overstock.android.prototype.ui.adapter;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by itowey on 07/04/16.
 */
@RunWith(RobolectricTestRunner.class)
public class NavDrawerRecyclerViewAdapterTest {

    @Mock Context context;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Assert.assertNotNull(context);
    }
    @Test
    public void testConstructor(){
        NavDrawerRecyclerViewAdapter navDrawerRecyclerViewAdapter = new NavDrawerRecyclerViewAdapter(context,null);
        Assert.assertNotNull(navDrawerRecyclerViewAdapter);
    }

    @Test
    public void test_onCreateViewHolder() {

    }
}
