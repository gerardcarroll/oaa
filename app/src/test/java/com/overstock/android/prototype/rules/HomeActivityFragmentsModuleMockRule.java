package com.overstock.android.prototype.rules;

import com.overstock.android.prototype.component.HomeActivityComponent;
import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.module.HomeActivityFragmentsModule;

import org.mockito.Mockito;

import it.cosenonjaviste.daggermock.DaggerMockRule;

/**
 * Created by itowey on 15/03/16.
 */
public class HomeActivityFragmentsModuleMockRule extends DaggerMockRule<HomeActivityComponent>  {
    public HomeActivityFragmentsModuleMockRule() {
        super(HomeActivityComponent.class, new HomeActivityFragmentsModule());
        GoogleFederatedIdentityFragment spyGoogleFederatedIdentityFragment = Mockito.spy(new GoogleFederatedIdentityFragment());
        provides(GoogleFederatedIdentityFragment.class, spyGoogleFederatedIdentityFragment);
        set(new ComponentSetter<HomeActivityComponent>(){

            @Override
            public void setComponent(HomeActivityComponent homeActivityComponent) {

            }
        });
    }
}
