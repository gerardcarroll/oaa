package com.overstock.android.prototype.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by itowey on 25/03/16.
 */
public class ProductsResponseTest {

    @Test
    public void noArgConstructorTest(){
        ProductsResponse productsResponse = new ProductsResponse();
        Assert.assertNotNull(productsResponse);
    }

    @Test
    public void argConstructorTest(){
        ProductsResponse productsResponse = new ProductsResponse(new Products());
        Assert.assertNotNull(productsResponse);
    }

    @Test
    public void productsGetterTest(){
        ProductsResponse productsResponse = new ProductsResponse(new Products());
        Assert.assertNotNull(productsResponse.getProducts());
    }

}
