package com.overstock.android.prototype.component;

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
public interface HomeActivityComponent extends HomeActivityInject {

    final class Initializer {
        public static HomeActivityComponent init() {
            return DaggerHomeActivityComponent.builder()
                    .build();
        }
    }
}
