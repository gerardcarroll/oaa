package com.overstock.android.prototype.expresso.component;

import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.module.GoogleApiClientModule;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Component;

/**
 * Created by itowey on 10/03/16.
 */
@ActivityScope
@Component(
        modules = {GoogleApiClientModule.class}
)
public interface GoogleApiClientComponent extends GoogleApiClientInject{

        public final static class Initializer {
                public static GoogleApiClientComponent init(HomeActivity homeActivity) {
                        return DaggerGoogleApiClientComponent.builder()
                                .googleApiClientModule(new GoogleApiClientModule(homeActivity))
                                .build();
                }
        }
}
