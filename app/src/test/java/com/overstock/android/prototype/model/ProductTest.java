package com.overstock.android.prototype.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author itowey on 25/03/16.
 */
public class ProductTest {

    @Test
    public void noArgConstructorTest(){
        Product product = new Product();
        Assert.assertNotNull(product);
    }

    @Test
    public void argConstructorTest(){
        Product product = new Product(1,"s1","s2","s3","s4",0.01f);
        Assert.assertNotNull(product);
    }

    @Test
    public void productGetterTest(){
        Product product = new Product(1,"imageLarge","imageMedium","imageThumbnail","name",0.1F);
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getId(), 1);
        Assert.assertEquals(product.getImageLarge(), "imageLarge");
        Assert.assertEquals(product.getImageMedium1(), "imageMedium");
        Assert.assertEquals(product.getImageThumbnail(),"imageThumbnail");
        Assert.assertEquals(product.getName(), "name");
        Assert.assertEquals(product.getMemberPrice(), 0.1F);
    }

}
