package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.client.TheOAppClient;
import com.overstock.android.prototype.model.ProductDataService;
import com.overstock.android.prototype.module.scope.ApplicationScope;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.presenter.impl.BrandPresenterImpl;
import com.overstock.android.prototype.presenter.FeedPresenter;
import com.overstock.android.prototype.presenter.impl.FeedPresenterImpl;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.presenter.impl.ProductDetailPresenterImpl;
import com.overstock.android.prototype.service.OappGoogleAuthService;
import com.overstock.android.prototype.service.ProductService;

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
  public FeedPresenter feedPresenter() {
    return new FeedPresenterImpl();
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

}
