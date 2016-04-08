package com.overstock.android.prototype.model;

import static org.mockito.Matchers.any;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author RayConnolly Created on 4/5/2016.
 */
public class CommunityTest {

    @Test
    public void noArgConstructorTest(){
        Community community = new Community();
        Assert.assertNotNull(community);
    }

    @Test
    public void argConstructorTest(){
        Community community = new Community(any(Integer.class),any(String.class));
        Assert.assertNotNull(community);
    }

    @Test
    public void communityGetterSetterTest(){
        Community community = new Community();
        Assert.assertNotNull(community);
        community.setImageId(1);
        Assert.assertEquals(community.getImageId(), 1);
        community.setName("Watches");
        Assert.assertEquals(community.getName(), "Watches");
        community.setSelected(true);
        Assert.assertEquals(community.isSelected(), true);
    }
}
