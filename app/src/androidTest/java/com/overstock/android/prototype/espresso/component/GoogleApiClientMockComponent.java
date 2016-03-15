package com.overstock.android.prototype.espresso.component;

import com.overstock.android.prototype.espresso.module.GoogleApiClientMockModule;
import com.overstock.android.prototype.expresso.component.GoogleApiClientInject;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Component;

/**
 * Created by itowey on 11/03/16.
 */
@ActivityScope
@Component(
        modules = {GoogleApiClientMockModule.class}
)
public interface GoogleApiClientMockComponent  extends GoogleApiClientInject {
}
