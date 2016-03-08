package com.overstock.android.prototype.component;

import javax.inject.Singleton;

import com.overstock.android.prototype.BrandFragment;
import com.overstock.android.prototype.activity.HomeActivity;
import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.module.ApplicationModule;

import dagger.Component;

/**
 * @author LeeMeehan Created on 03-Mar-16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface DIComponent {

  void inject(final HomeActivity homeActivity);

  void inject(final HomeFragment homeFragment);

  void inject(final GoogleFederatedIdentityFragment googleFederatedIdentityFragment);

  void inject(final BrandFragment brandFragment);
}
