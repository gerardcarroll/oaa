package com.overstock.android.prototype.main;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.VisibleForTesting;

import com.crashlytics.android.Crashlytics;
import com.overstock.android.prototype.BuildConfig;
import com.overstock.android.prototype.component.ApplicationComponent;

import io.fabric.sdk.android.Fabric;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
public class OAppPrototypeApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        //"StrictMode" is a developer tool which detects things you might be doing by accident and
        // brings them to your attention so you can fix them.
        if ( Build.VERSION.SDK_INT >= 9 && BuildConfig.DEBUG ) {
            StrictMode.enableDefaults();
        }
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        //Dagger init
        component = ApplicationComponent.Initializer.init(this);

    }

    public static OAppPrototypeApplication get(Context context) {
        return (OAppPrototypeApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(ApplicationComponent component) {
        this.component = component;
    }

}
