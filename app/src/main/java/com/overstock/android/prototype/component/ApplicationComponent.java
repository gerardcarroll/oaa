package com.overstock.android.prototype.component;

import android.app.Application;

import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.module.scope.ApplicationScope;

import dagger.Component;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
@ApplicationScope
@Component(
        modules = {ApplicationModule.class})
public interface ApplicationComponent extends ApplicationInject {

    final class Initializer{
        public static ApplicationComponent init(Application application){
            return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(application)).build();
        }
    }
}
