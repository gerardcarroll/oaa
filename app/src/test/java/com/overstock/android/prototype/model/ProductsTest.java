package com.overstock.android.prototype.model;

import com.overstock.android.prototype.models.Product;
import com.overstock.android.prototype.models.Products;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by itowey on 25/03/16.
 */
public class ProductsTest {

    @Test
    public void noArgConstructorTest(){
        Products products = new Products();
        Assert.assertNotNull(products);
    }

    @Test
    public void argConstructorTest(){
        Products products = new Products(new ArrayList<Product>());
        Assert.assertNotNull(products);
    }

    @Test
    public void productsGetterTest(){
        Products products = new Products(new ArrayList<Product>());
        Assert.assertNotNull(products.getProductsList());
    }

}
