package com.overstock.android.prototype;

import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.module.HomeActivityFragmentsModule;

import org.mockito.Mockito;

/**
 * @author LeeMeehan
 */
public class TestModule extends HomeActivityFragmentsModule {
    @Override
    public GoogleFederatedIdentityFragment providesGoogleFederatedIdentityFragment() {
        return Mockito.mock(GoogleFederatedIdentityFragment.class);
    }
}
