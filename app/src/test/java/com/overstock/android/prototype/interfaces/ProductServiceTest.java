package com.overstock.android.prototype.interfaces;

import android.util.Log;

import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.Products;
import com.overstock.android.prototype.model.ProductsResponse;
import com.overstock.android.prototype.service.ProductService;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by itowey on 22/03/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {


    private static final String TAG = ProductServiceTest.class.getName();

    @Mock
    ProductService mockProductService;

    @Before
    public void setUp() {

        when(mockProductService.getBestSellers(any(String.class))).thenReturn(Observable.just(new ProductsResponse(new Products(new ArrayList<Product>() {{
            add(new Product(1,"http://nfl.product.image.ostk.com/large","http://nfl.product.image.ostk.com", "nfl.product_image.png", 123.99F));
            add(new Product(2,"http://nhl.product.image.ostk.com/large", "http://nhl.product.image.ostk.com", "nhl.product_image.png", 25.01F));
        }}))));
        when(mockProductService.getNewArrivals(any(String.class))).thenReturn(Observable.just(new ProductsResponse(new Products(new ArrayList<Product>() {{
            add(new Product(3,"http://nfl.product.image.ostk.com/large", "http://nfl.product.image.ostk.com", "new.nfl.product_image.png", 13.99F));
            add(new Product(4,"http://nhl.product.image.ostk.com/large", "http://nhl.product.image.ostk.com", "new.nhl.product_image.png", 15.01F));
        }}))));
        when(mockProductService.query(any(String.class), any(String.class), any(Integer.class))).thenReturn(Observable.just(new ProductsResponse(new Products(new ArrayList<Product>() {{
            add(new Product(5,"http://nfl.product.image.ostk.com/large","http://nfl.product.image.ostk.com", "nfl.product_image.png", 3.99F));
            add(new Product(6,"http://nhl.product.image.ostk.com/large", "http://nhl.product.image.ostk.com", "nhl.product_image.png", 2.01F));
        }}))));
    }

    @Test
    public void testBestSellers() {

        mockProductService.getBestSellers("dummy")
                .subscribe(new Observer<ProductsResponse>() {
                               @Override
                               public void onCompleted() {
                                   Log.i(TAG, "Complete");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e(TAG, "ERROR");
                               }

                               @Override
                               public void onNext(ProductsResponse productsResponse) {
                                   Assert.assertNotNull(productsResponse);
                                   Assert.assertNotNull(productsResponse.getProducts());
                                   Assert.assertNotNull(productsResponse.getProducts().getProductsList());
                                   Assert.assertEquals(productsResponse.getProducts().getProductsList().size(), 2);
                                   for (Product product : productsResponse.getProducts().getProductsList()) {
                                       Assert.assertNotNull(product);
                                       Assert.assertTrue(product.getId() > 0);
                                       Assert.assertNotNull(product.getImageMedium1());
                                       Assert.assertTrue(product.getImageMedium1().contains("image.ostk"));
                                       Assert.assertNotNull(product.getMemberPrice());
                                       Assert.assertTrue(product.getMemberPrice() > 0);
                                       Assert.assertNotNull(product.getName());
                                       Assert.assertTrue(product.getName().contains("product_image"));
                                   }
                               }
                           }
                );
    }

    @Test
    public void testNewArrivals() {
        mockProductService.getNewArrivals("dummy")
                .subscribe(new Observer<ProductsResponse>() {
                               @Override
                               public void onCompleted() {
                                   Log.i(TAG, "Complete");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e(TAG, "ERROR");
                               }

                               @Override
                               public void onNext(ProductsResponse productsResponse) {
                                   Assert.assertNotNull(productsResponse);
                                   Assert.assertNotNull(productsResponse.getProducts());
                                   Assert.assertNotNull(productsResponse.getProducts().getProductsList());
                                   Assert.assertEquals(productsResponse.getProducts().getProductsList().size(), 2);
                                   for (Product product : productsResponse.getProducts().getProductsList()) {
                                       Assert.assertNotNull(product);
                                       Assert.assertNotNull(product.getImageMedium1());
                                       Assert.assertTrue(product.getImageMedium1().contains("image.ostk"));
                                       Assert.assertNotNull(product.getMemberPrice());
                                       Assert.assertTrue(product.getMemberPrice() > 0);
                                       Assert.assertNotNull(product.getName());
                                       Assert.assertTrue(product.getName().contains("product_image"));
                                   }
                               }
                           }
                );
    }

    @Test
    public void testQuery() {
        mockProductService.query("dummy", "dummy2", 10)
                .subscribe(new Observer<ProductsResponse>() {
                               @Override
                               public void onCompleted() {
                                   Log.i(TAG, "Complete");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e(TAG, "ERROR");
                               }

                               @Override
                               public void onNext(ProductsResponse productsResponse) {
                                   Assert.assertNotNull(productsResponse);
                                   Assert.assertNotNull(productsResponse.getProducts());
                                   Assert.assertNotNull(productsResponse.getProducts().getProductsList());
                                   Assert.assertEquals(productsResponse.getProducts().getProductsList().size(), 2);
                                   for (Product product : productsResponse.getProducts().getProductsList()) {
                                       Assert.assertNotNull(product);
                                       Assert.assertNotNull(product.getImageMedium1());
                                       Assert.assertTrue(product.getImageMedium1().contains("image.ostk"));
                                       Assert.assertNotNull(product.getMemberPrice());
                                       Assert.assertTrue(product.getMemberPrice() > 0);
                                       Assert.assertNotNull(product.getName());
                                       Assert.assertTrue(product.getName().contains("product_image"));
                                   }
                               }
                           }
                );
    }
}
