package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.client.TheOAppClient;
import com.overstock.android.prototype.interfaces.CommunityClient;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.module.scope.ApplicationScope;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.presenter.CommunityPresenter;
import com.overstock.android.prototype.presenter.ProductBottomSheetPresenter;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.presenter.impl.BrandPresenterImpl;
import com.overstock.android.prototype.presenter.impl.CommunityPresenterImpl;
import com.overstock.android.prototype.presenter.impl.ProductBottomSheetPresenterImpl;
import com.overstock.android.prototype.presenter.impl.ProductDetailPresenterImpl;
import com.overstock.android.prototype.service.CommunityService;
import com.overstock.android.prototype.service.OappGoogleAuthService;
import com.overstock.android.prototype.service.ProductService;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
@Module
public class ApplicationModule {

  Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  @ApplicationScope
  public Application providesApplication() {
    return application;
  }

  @Provides
  @ApplicationScope
  public BrandPresenter providesBrandPresenter(final ProductDataService productDataService) {
    return new BrandPresenterImpl(productDataService);
  }

  @Provides
  @ApplicationScope
  public ProductDetailPresenter productDetailPresenter(final Application applicationContext,
    final ProductDataService productDataService) {
    return new ProductDetailPresenterImpl(applicationContext, productDataService);
  }

  @Provides
  @ApplicationScope
  public ProductDataService providesProductDataService(final ProductService productService) {
    return new ProductDataService(productService);
  }

  @Provides
  @ApplicationScope
  public CommunityPresenter communitiesPresenter(CommunityService communityService) {
    return new CommunityPresenterImpl(communityService);
  }

  @Provides
  @ApplicationScope
  public CommunityClient providesCommunityClient(Application application) {
    return new CommunityClient(application.getApplicationContext());
  }

  @Provides
  @ApplicationScope
  public TheOAppClient providesTheOAppClient(Application application) {
    return new TheOAppClient(application.getApplicationContext());
  }

  @Provides
  @ApplicationScope
  public CommunityService providesCommunityService(final CommunityClient communityClient) {
    return communityClient.getClient();
  }

  @Provides
  @ApplicationScope
  public ProductBottomSheetPresenter productBottomSheetPresenter() {
    return new ProductBottomSheetPresenterImpl();
  }

  @Provides
  @ApplicationScope
  public ProductService providesProductService(final TheOAppClient theOAppClient) {
    return theOAppClient.getClient();
  }

  @Provides
  @ApplicationScope
  public OappGoogleAuthService providesOappGoogleAuthService(final Application application) {
    return new OappGoogleAuthService(application);
  }

  @Provides
  @ApplicationScope
  public Picasso providesPicasso(final Application application) {
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final int cacheSize = maxMemory / 8;
    final Picasso picasso = new Picasso.Builder(application).memoryCache(new LruCache(cacheSize)).build();
    return picasso;
  }

}
