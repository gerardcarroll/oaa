package com.overstock.android.prototype.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by itowey on 25/03/16.
 */
public class FeedTest {

    @Test
    public void noArgConstructorTest(){
        Feed feed = new Feed();
        Assert.assertNotNull(feed);
    }

    @Test
    public void argConstructorTest(){
        Feed feed = new Feed(1,"£","%");
        Assert.assertNotNull(feed);
    }

    @Test
    public void feedGetterSetterTest(){
        Feed feed = new Feed();
        Assert.assertNotNull(feed);
        feed.setProductImage(1);
        Assert.assertEquals(feed.getProductImage(), 1);
        feed.setProductUrl("img.ostk.com");
        Assert.assertEquals(feed.getProductUrl(), "img.ostk.com");
        feed.setTopProductsLink("img.ostk.com/prods");
        Assert.assertEquals(feed.getTopProductsLink(),"img.ostk.com/prods");
    }

}
