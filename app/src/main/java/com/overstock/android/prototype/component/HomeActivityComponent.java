package com.overstock.android.prototype.component;

import android.content.Context;

import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.module.HomeActivityModule;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Component;

/**
 * Created by itowey on 11/03/16.
 */
@ActivityScope
@Component(
        dependencies = { ApplicationComponent.class },
        modules = { HomeActivityModule.class }
)
public interface HomeActivityComponent extends HomeActivityInject {

  final class Initializer {
    public static HomeActivityComponent init(Context context) {
      return DaggerHomeActivityComponent.builder()
                .applicationComponent(OAppPrototypeApplication.get(context).getComponent())
              .build();
    }
  }
}
