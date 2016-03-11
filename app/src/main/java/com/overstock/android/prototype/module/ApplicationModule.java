package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.module.scope.ApplicationScope;

import javax.inject.Singleton;

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
  @Singleton
  Application providesApplication(){
    return application;
  }

}
