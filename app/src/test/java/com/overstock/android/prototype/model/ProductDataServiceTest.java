package com.overstock.android.prototype.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.overstock.android.prototype.client.TheOAppClient;
import com.overstock.android.prototype.provider.OappProviderContract;
import com.overstock.android.prototype.service.ProductService;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import rx.Observable;
import rx.Observer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by itowey on 22/03/16.
 */
@RunWith(RobolectricTestRunner.class)
public class ProductDataServiceTest {

    private ProductDataService productDataService;

    @Mock
    Context context;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(context.getString(any(Integer.class))).thenReturn("http://www.overstock.com/api/");
        productDataService = new ProductDataService(new TheOAppClient(context).getClient());
    }

    @After
    public void tearDown(){
        productDataService = null;
    }
    @Test
    public void testInstanciated(){
        Assert.assertNotNull(productDataService);
    }

    @Test
    public void testQuerySortedByBestSellers() {
        testQuery(OappProviderContract.ProductEntry.BEST_SELLERS, "nfl");
    }

    @Test
    public void testQuerySortedByNewArrivals() {
        testQuery(OappProviderContract.ProductEntry.NEW_ARRIVALS, "football jersey");
    }

    private void testQuery(String sortOrder, String keywords){
        Observable<ProductsResponse> bestSellers = productDataService.query(Uri.parse("content://com.overstock.android.prototype/products/" + sortOrder).buildUpon()
                        .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_COUNT, "30")
                        .appendQueryParameter(ProductService.PRODUCT_SERVICE_QUERY_PARAM_NAME_KEYWORDS, keywords)
                        .build()
        );

        bestSellers.subscribe(new Observer<ProductsResponse>() {
              @Override
              public void onCompleted() {
                  Log.i(this.getClass().getName(), "Complete");
              }

              @Override
              public void onError(Throwable e) {
                  Log.e(this.getClass().getName(), "ERROR");
              }

              @Override
              public void onNext(ProductsResponse productsResponse) {
                  Assert.assertNotNull(productsResponse);
                  Assert.assertNotNull(productsResponse.getProducts());
                  Assert.assertNotNull(productsResponse.getProducts().getProductsList());
                  Assert.assertEquals(productsResponse.getProducts().getProductsList().size(), 30);
                  for (Product product : productsResponse.getProducts().getProductsList()) {
                      Assert.assertNotNull(product);
                      Assert.assertNotNull(product.getImageMedium1());
                      Assert.assertTrue(product.getImageMedium1().contains(".jpg"));
                      Assert.assertNotNull(product.getMemberPrice());
                      Assert.assertTrue(product.getMemberPrice() > 0);
                      Assert.assertNotNull(product.getName());
                  }
              }
          }
        );

    }
}
