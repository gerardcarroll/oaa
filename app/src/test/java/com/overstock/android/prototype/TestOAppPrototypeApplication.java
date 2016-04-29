package com.overstock.android.prototype;

import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.main.OAppPrototypeApplication;

import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

/**
 * Created by itowey on 27/04/16.
 */
public class TestOAppPrototypeApplication extends OAppPrototypeApplication implements TestLifecycleApplication {

    @Override
    public void onCreate() {
        // Dagger init
        component = ApplicationComponent.Initializer.init(this);

    }

    @Override
    public void beforeTest(Method method) {
    }

    @Override
    public void prepareTest(Object test) {
    }

    @Override
    public void afterTest(Method method) {
    }

}