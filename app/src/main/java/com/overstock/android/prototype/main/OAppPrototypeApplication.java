package com.overstock.android.prototype.main;

import android.app.Application;

import com.overstock.android.prototype.component.ApplicationComponent;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
public class OAppPrototypeApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        //Dagger init
        component = ApplicationComponent.Initializer.init(this);
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
