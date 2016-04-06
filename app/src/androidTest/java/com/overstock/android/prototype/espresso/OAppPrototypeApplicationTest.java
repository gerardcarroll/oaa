package com.overstock.android.prototype.espresso;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.overstock.android.prototype.R;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.espresso.dagger.rules.OAppPrototypeApplicationMockRule;
import com.overstock.android.prototype.service.ProductService;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.model.Product;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.model.Products;
import com.overstock.android.prototype.model.ProductsResponse;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.service1.OappGoogleAuthService;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class OAppPrototypeApplicationTest {

    private static final String TAG = OAppPrototypeApplicationTest.class.getName();
    //Dagger Mocking Rule
    @Rule public OAppPrototypeApplicationMockRule oAppPrototypeApplicationTest2Rule = new OAppPrototypeApplicationMockRule();

    @Rule public ActivityTestRule<HomeActivity> homeActivityRule = new ActivityTestRule<>(HomeActivity.class, false, false);

    @Mock BrandPresenter brandPresenter;
    @Mock ProductDataService productDataService;
    @Mock ProductService productService;
    @Mock OappGoogleAuthService oappGoogleAuthService;

    private OAppPrototypeApplication getApp(){
        return (OAppPrototypeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Test
    public void daggerInjectMockTest() {
        homeActivityRule.launchActivity(null);
        Assert.assertNotNull(getApp().getComponent());
        Assert.assertNotNull(brandPresenter);
        Assert.assertNotNull(productDataService);
        Assert.assertNotNull(productService);
        Assert.assertNotNull(oappGoogleAuthService);
    }

    @Test
    public void oappGoogleAuthServiceMockTest() {
        GoogleApiClient mockGoogleApiClient = Mockito.mock(GoogleApiClient.class);
        when(oappGoogleAuthService.getGoogleApiClient()).thenReturn(mockGoogleApiClient);
        when(mockGoogleApiClient.isConnected()).thenReturn(true);
        Assert.assertTrue(mockGoogleApiClient.isConnected());
    }

    @Test
    public void productServiceMockTest(){
        when(productService.getBestSellers(any(String.class))).thenReturn(Observable.just(new ProductsResponse(new Products(new ArrayList<Product>() {{
            add(new Product(1,"http://nfl.product.image.ostk.com", "nfl.product_image.png", 123.99F));
            add(new Product(2, "http://nhl.product.image.ostk.com", "nhl.product_image.png", 25.01F));
        }}))));

        productService.getBestSellers("dummy").subscribe(new Observer<ProductsResponse>() {
             @Override
             public void onCompleted() {
                 Log.i(TAG,"Complete");
             }

             @Override
             public void onError(Throwable e) {
                 Log.e(TAG,"ERROR");
             }

             @Override
             public void onNext(ProductsResponse productsResponse) {
                 Assert.assertNotNull(productsResponse);
                 Assert.assertNotNull(productsResponse.getProducts());
                 Assert.assertNotNull(productsResponse.getProducts().getProductsList());
                 Assert.assertEquals(productsResponse.getProducts().getProductsList().size(), 2);
                 for(Product product : productsResponse.getProducts().getProductsList()){
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
    public void homeActivityDisplayed_SUCCESS() {
        homeActivityRule.launchActivity(null);
        onView(withId(R.id.home_activity)).check(matches(isDisplayed()));
    }

}


