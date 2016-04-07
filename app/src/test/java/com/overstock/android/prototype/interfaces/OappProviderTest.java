package com.overstock.android.prototype.interfaces;

import android.net.Uri;
import android.util.Log;

import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.Products;
import com.overstock.android.prototype.model.ProductsResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

import static org.mockito.Mockito.when;

/**
 * Created by itowey on 22/03/16.
 */
@RunWith(RobolectricTestRunner.class)
//@RunWith(MockitoJUnitRunner.class)
public class OappProviderTest {
    private static final String TAG = OappProviderTest.class.getName();

    @Mock
    OappProvider<ProductsResponse> mockOappProvider;

    @Mock Uri uri;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockOappProvider.query(uri)).thenReturn(Observable.just(new ProductsResponse(new Products(new ArrayList<Product>() {{
            add(new Product(1,"http://nfl.product.image.ostk.com/large","http://nfl.product.image.ostk.com", "nfl.product_image.png", 3.99F));
            add(new Product(2,"http://nhl.product.image.ostk.com/large","http://nhl.product.image.ostk.com", "nhl.product_image.png", 2.01F));
        }}))));

    }

    @Test
    public void testQuery() {
        mockOappProvider.query(uri)
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