package com.overstock.android.prototype.main;

import android.app.Application;

import com.overstock.android.prototype.component.DIComponent;
import com.overstock.android.prototype.component.DaggerDIComponent;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
public class OAppPrototypeApplication extends Application {
    DIComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDIComponent.builder().build();
    }

    public DIComponent getComponent() {
        return component;
    }
}
