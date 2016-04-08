package com.overstock.android.prototype.ui.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by itowey on 07/04/16.
 */
public class NavDrawerItemTest {

    @Test
    public void constructorTest(){
        NavDrawerItem navDrawerItem = new NavDrawerItem();
        Assert.assertNotNull(navDrawerItem);
    }

    @Test
    public void getSetTest(){
        NavDrawerItem navDrawerItem = new NavDrawerItem();
        navDrawerItem.setEnabled(true);
        navDrawerItem.setIcon("icon");
        navDrawerItem.setTitle("title");
        Assert.assertTrue(navDrawerItem.isEnabled());
        Assert.assertEquals(navDrawerItem.getIcon(), "icon");
        Assert.assertEquals(navDrawerItem.getTitle(),"title");
    }

}
