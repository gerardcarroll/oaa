package com.overstock.android.prototype.module;

import javax.inject.Singleton;

import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.presenter.BrandPresenter;
import com.overstock.android.prototype.presenter.BrandPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
@Module
public class ApplicationModule {

  @Provides
  @Singleton
  public HomeFragment homeFragment() {
    return new HomeFragment();
  }

  @Provides
  @Singleton
  public BrandPresenter brandPresenter() {
    return new BrandPresenterImpl();
  }

}
