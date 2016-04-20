package com.overstock.android.prototype.module;

import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.fragment.SignInWithEmailFragment;
import com.overstock.android.prototype.fragment.SignUpWithEmailFragment;
import com.overstock.android.prototype.module.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itowey on 11/03/16.
 */
@Module
public class HomeActivityFragmentsModule {

  @Provides
  @ActivityScope
  public HomeFragment providesHomeFragment() {
    return new HomeFragment();
  }

  @Provides
  @ActivityScope
  public GoogleFederatedIdentityFragment providesGoogleFederatedIdentityFragment() {
    return new GoogleFederatedIdentityFragment();
  }

  @Provides
  @ActivityScope
  public SignInWithEmailFragment providesSignInWithEmailFragment() {
    return new SignInWithEmailFragment();
  }

}
