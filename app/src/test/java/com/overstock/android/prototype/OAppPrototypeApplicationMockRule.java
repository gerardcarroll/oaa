package com.overstock.android.prototype;

import android.app.Application;

import com.overstock.android.prototype.component.ApplicationComponent;
import com.overstock.android.prototype.module.ApplicationModule;
import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.service.OappGoogleAuthService;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Mockito.mock;

/**
 * Created by itowey on 15/03/16.
 */
public class OAppPrototypeApplicationMockRule extends DaggerMockRule<ApplicationComponent> {

    public OAppPrototypeApplicationMockRule()  {


        super(ApplicationComponent.class, new ApplicationModule(mock(Application.class)));
        providesMock(OappGoogleAuthService.class, SignUpWithEmailPresenter.class);

    }
}