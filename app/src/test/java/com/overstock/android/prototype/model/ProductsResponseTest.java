package com.overstock.android.prototype.model;

import com.overstock.android.prototype.models.Products;
import com.overstock.android.prototype.models.ProductsResponse;

import junit.framework.Assert;

import org.junit.Test;

import static org.mockito.Mockito.mock;

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
        ProductsResponse productsResponse = new ProductsResponse(mock(Products.class));
        Assert.assertNotNull(productsResponse);
    }

    @Test
    public void productsGetterTest(){
        ProductsResponse productsResponse = new ProductsResponse(mock(Products.class));
        Assert.assertNotNull(productsResponse.getProducts());
    }

}
