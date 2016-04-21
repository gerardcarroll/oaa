package com.overstock.android.prototype.presenter.impl;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.overstock.android.prototype.presenter.SignInWithEmailPresenter;
import com.overstock.android.prototype.service.ParseService;
import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * Created by gcarroll on 20/04/2016.
 */
public class SignInWithEmailPresenterImpl implements SignInWithEmailPresenter {

  private static final String TAG = SignInWithEmailPresenterImpl.class.getName();

  private SignInWithEmailView signInWithEmailView;

  private Context context;

  private Subscription subscription = Subscriptions.empty();

  private ParseService parseService;

  public SignInWithEmailPresenterImpl(Context context, ParseService parseService) {
    this.parseService = parseService;
    this.context = context;
  }

  @Override
  public void setView(SignInWithEmailView signInWithEmailView) {
    this.signInWithEmailView = signInWithEmailView;
  }

  @Override
  public void onSignIn(final String username, final String password) {
    parseService.loginParseUser(username, password);
  }

  @Override
  public void onSignInError(String message) {
    Log.d(TAG, "Error signing in...");
    Toast.makeText(context, "Sign In Error: " + message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onDestroy() {
    this.signInWithEmailView = null;
  }
}
