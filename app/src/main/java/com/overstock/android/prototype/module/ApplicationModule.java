package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.interfaces.ProductService;
import com.overstock.android.prototype.interfaces.TheOAppClient;
import com.overstock.android.prototype.models.ProductDataService;
import com.overstock.android.prototype.module.scope.ApplicationScope;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.presenter.BrandPresenterImpl;
import com.overstock.android.prototype.presenter.ProductDetailPresenter;
import com.overstock.android.prototype.presenter.ProductDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
@Module
@ApplicationScope
public class ApplicationModule {

  Application application;

  public ApplicationModule(Application application){
    this.application = application;
  }

  @Provides
  Application providesApplication(){
    return application;
  }

  @Provides
  public BrandPresenter brandPresenter(final ProductDataService productDataService) {
    return new BrandPresenterImpl(productDataService);
  }

  @Provides
  public ProductDetailPresenter productDetailPresenter(final ProductDataService productDataService){
    return new ProductDetailPresenterImpl(productDataService);
  }

  @Provides
  public ProductDataService productDataService(final ProductService productService){
    return new ProductDataService(productService);
  }

  @Provides
  public ProductService productService() {
    return TheOAppClient.getClient();
  }

}
