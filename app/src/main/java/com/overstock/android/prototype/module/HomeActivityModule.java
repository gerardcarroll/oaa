package com.overstock.android.prototype.module;

import android.app.Application;

import com.overstock.android.prototype.fragment.GoogleFederatedIdentityFragment;
import com.overstock.android.prototype.fragment.HomeFragment;
import com.overstock.android.prototype.module.scope.ActivityScope;
import com.overstock.android.prototype.presenter.SignInWithEmailPresenter;
import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.presenter.impl.SignInWithEmailPresenterImpl;
import com.overstock.android.prototype.presenter.impl.SignUpWithEmailPresenterImpl;
import com.overstock.android.prototype.service.ParseService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itowey on 11/03/16.
 */
@Module
public class HomeActivityModule {

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
  public ParseService providesParseService(final Application applicationContext) {
    return new ParseService(applicationContext);
  }


  @Provides
  @ActivityScope
  public SignUpWithEmailPresenter signUpWithEmailPresenter(ParseService parseService) {
    return new SignUpWithEmailPresenterImpl(parseService);
  }

  @Provides
  @ActivityScope
  public SignInWithEmailPresenter signInWithEmailPresenter(ParseService parseService) {
    return new SignInWithEmailPresenterImpl(parseService);
  }
}
