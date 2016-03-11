package com.overstock.android.prototype.component;

import android.app.Application;

import com.overstock.android.prototype.fragment.BrandFragment;
import com.overstock.android.prototype.models.ProductDataService;
import com.overstock.android.prototype.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(final BrandFragment brandFragment);

    void inject(final ProductDataService productDataService);

    public final static class Initializer{
        public static ApplicationComponent init(Application application){
            return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(application)).build();
        }
    }
}
