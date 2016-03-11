package com.overstock.android.prototype.expresso.component;

import com.overstock.android.prototype.module.HomeActivityFragmentsModule;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Component;

/**
 * Created by itowey on 11/03/16.
 */
@ActivityScope
@Component(
        modules = {HomeActivityFragmentsModule.class}
)
public interface HomeActivityComponent extends HomeActivityInject{

    public final static class Initializer {
        public static HomeActivityComponent init() {
            return DaggerHomeActivityComponent.builder()
                    .build();
        }
    }
}
