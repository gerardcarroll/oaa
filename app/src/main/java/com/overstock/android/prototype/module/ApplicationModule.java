package com.overstock.android.prototype.module;

import com.overstock.android.prototype.fragment.HomeFragment;

import javax.inject.Singleton;

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



}
