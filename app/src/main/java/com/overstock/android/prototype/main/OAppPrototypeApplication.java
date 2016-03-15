package com.overstock.android.prototype.main;

import android.app.Application;

import com.overstock.android.prototype.expresso.component.ApplicationComponent;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
public class OAppPrototypeApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        //Dagger init
        component = ApplicationComponent.Initializer.init(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
