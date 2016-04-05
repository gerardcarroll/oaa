package com.overstock.android.prototype.model;

import com.overstock.android.prototype.models.Product;

import junit.framework.Assert;

import org.junit.Test;

import static org.mockito.Matchers.any;

/**
 * Created by itowey on 25/03/16.
 */
public class ProductTest {

    @Test
    public void noArgConstructorTest(){
        Product product = new Product();
        Assert.assertNotNull(product);
    }

    @Test
    public void argConstructorTest(){
        Product product = new Product(any(Integer.class),any(String.class),any(String.class),any(Float.class));
        Assert.assertNotNull(product);
    }

    @Test
    public void productGetterTest(){
        Product product = new Product(1,"s1","s2",0.1F);
        Assert.assertNotNull(product);
        Assert.assertEquals(product.getId(), 1);
        Assert.assertEquals(product.getImageMedium1(), "s1");
        Assert.assertEquals(product.getName(), "s2");
        Assert.assertEquals(product.getMemberPrice(), 0.1F);
    }

}
