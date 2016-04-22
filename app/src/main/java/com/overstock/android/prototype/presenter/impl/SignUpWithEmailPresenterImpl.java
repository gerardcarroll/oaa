package com.overstock.android.prototype.presenter.impl;

import android.util.Log;

import com.overstock.android.prototype.presenter.SignUpWithEmailPresenter;
import com.overstock.android.prototype.service.ParseService;
import com.overstock.android.prototype.view.SignUpWithEmailView;

/**
 * Created by rconnolly on 4/19/2016.
 */
public class SignUpWithEmailPresenterImpl implements SignUpWithEmailPresenter {

  private static final String TAG = SignUpWithEmailPresenterImpl.class.getName();

  private SignUpWithEmailView connectWithEmailView;

  private ParseService parseService;

  public SignUpWithEmailPresenterImpl(ParseService parseService) {
    this.parseService = parseService;
  }

  @Override
  public void setView(final SignUpWithEmailView connectWithEmailView) {
    this.connectWithEmailView = connectWithEmailView;
  }

  @Override
  public void onSignUp(final String username, final String password) {
    Log.d(TAG, "Calling ParseService for user SignUp");
    parseService.signUpNewParseUser(username, password);
  }

  @Override
  public void onDestroy() {
    connectWithEmailView = null;
  }
}
