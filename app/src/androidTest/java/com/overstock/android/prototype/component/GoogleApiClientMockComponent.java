package com.overstock.android.prototype.component;

import com.overstock.android.prototype.module.GoogleApiClientMockModule;
import com.overstock.android.prototype.module.scope.PerActivity;

import dagger.Component;

/**
 * Created by itowey on 11/03/16.
 */
@PerActivity
@Component(
        modules = {GoogleApiClientMockModule.class}
)
public interface GoogleApiClientMockComponent  extends GoogleApiClientInject {
}
