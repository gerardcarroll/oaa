package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.client.FeedClient;
import com.overstock.android.prototype.client.TheOAppClient;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.module.scope.ApplicationScope;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.presenter.impl.BrandPresenterImpl;
import com.overstock.android.prototype.presenter.FeedPresenter;
import com.overstock.android.prototype.presenter.impl.FeedPresenterImpl;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.presenter.impl.ProductDetailPresenterImpl;
import com.overstock.android.prototype.service.FeedService;
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
@ApplicationScope
public class ApplicationModule {

  Application application;

  public ApplicationModule(Application application) {
    this.application = application;
  }

  @Provides
  public Application providesApplication() {
    return application;
  }

  @Provides
  public BrandPresenter providesBrandPresenter(final ProductDataService productDataService) {
    return new BrandPresenterImpl(productDataService);
  }

  @Provides
  public ProductDetailPresenter productDetailPresenter(final ProductDataService productDataService) {
    return new ProductDetailPresenterImpl(productDataService);
  }

  @Provides
  public FeedPresenter feedPresenter(final FeedService feedService) {
    return new FeedPresenterImpl(feedService);
  }

  @Provides
  public FeedClient feedClient(final Application application) {
    return new FeedClient(application);
  }

  @Provides
  public FeedService feedService(final FeedClient feedClient) {
    return feedClient.getClient();
  }

  public ProductDataService providesProductDataService(final ProductService productService) {
    return new ProductDataService(productService);
  }

  @Provides
  public ProductService providesProductService() {
    return TheOAppClient.getClient();
  }

  @Provides
  public OappGoogleAuthService providesOappGoogleAuthService(Application application) {
    return new OappGoogleAuthService(application);
  }

  @Provides
  public Picasso providesPicasso() {
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final int cacheSize = maxMemory / 8;
    final Picasso picasso = new Picasso.Builder(application.getBaseContext()).memoryCache(new LruCache(cacheSize))
        .build();
    return picasso;
  }

}
