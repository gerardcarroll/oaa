package com.overstock.android.prototype.presenter.impl;

import javax.inject.Inject;

import android.util.Log;

import com.overstock.android.prototype.presenter.SignInWithEmailPresenter;
import com.overstock.android.prototype.service.ParseService;
import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * Created by gcarroll on 20/04/2016.
 */
public class SignInWithEmailPresenterImpl implements SignInWithEmailPresenter {

  private static final String TAG = SignInWithEmailPresenterImpl.class.getName();

  private SignInWithEmailView signInWithEmailView;

  private ParseService parseService;

  @Inject
  public SignInWithEmailPresenterImpl(ParseService parseService) {
    this.parseService = parseService;
  }

  @Override
  public void setView(SignInWithEmailView signInWithEmailView) {
    this.signInWithEmailView = signInWithEmailView;
  }

  @Override
  public void onSignIn(final String username, final String password) {
    Log.d(TAG, "Calling Parse Service for user Sign In");
    parseService.loginParseUser(username, password, signInWithEmailView);
  }

  @Override
  public void onDestroy() {
    this.signInWithEmailView = null;
  }

  @Override
  public void validateCredentials(String username, String password) {
    if (username.isEmpty() || password.isEmpty()) {
      signInWithEmailView.displayToast("All fields are required.");
    }
    else {
      onSignIn(username, password);
    }
  }
}
