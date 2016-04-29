package com.overstock.android.prototype.presenter.impl;

import javax.inject.Inject;

import android.util.Log;

import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.service.ParseService;
import com.overstock.android.prototype.view.SignUpWithEmailView;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailPresenterImpl implements SignUpWithEmailPresenter {

  private static final String TAG = SignUpWithEmailPresenterImpl.class.getName();

  private SignUpWithEmailView signUpWithEmailView;

  private ParseService parseService;

  @Inject
  public SignUpWithEmailPresenterImpl(ParseService parseService) {
    this.parseService = parseService;
  }

  @Override
  public void setView(final SignUpWithEmailView signUpWithEmailView) {
    this.signUpWithEmailView = signUpWithEmailView;
  }

  @Override
  public void onSignUp(final String username, final String password) {
    Log.d(TAG, "Calling ParseService for user SignUp");
    parseService.signUpNewParseUser(username, password, signUpWithEmailView);
  }

  @Override
  public void onDestroy() {
    signUpWithEmailView = null;
  }

  @Override
  public void validateCredentials(String username, String password, String confirmPassword) {
    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
      signUpWithEmailView.displayToast("All fields are required.");
    }
    else if (!password.equals(confirmPassword)) {
      signUpWithEmailView.displayToast("Passwords do not match.");
    }
    else {
      onSignUp(username, password);
    }
  }
}
