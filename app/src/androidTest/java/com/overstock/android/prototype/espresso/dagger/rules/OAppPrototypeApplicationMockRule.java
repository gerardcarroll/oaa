package com.overstock.android.prototype.espresso.dagger.rules;

import android.app.Application;
import android.support.test.InstrumentationRegistry;

import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.main.OAppPrototypeApplication;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.service1.OappGoogleAuthService;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by itowey on 15/03/16.
 */
public class OAppPrototypeApplicationMockRule extends DaggerMockRule<ApplicationComponent> {

    public OAppPrototypeApplicationMockRule()  {
        super(ApplicationComponent.class, new ApplicationModule((Application) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext()));
        providesMock(OappGoogleAuthService.class);

        set(new ComponentSetter<ApplicationComponent>() {

            @Override
            public void setComponent(ApplicationComponent applicationComponent) {
                ((OAppPrototypeApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext()).setComponent(applicationComponent);
            }
        });
    }
}