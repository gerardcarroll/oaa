package com.overstock.android.prototype.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.overstock.android.prototype.activity.CommunityActivity;
import com.overstock.android.prototype.presenter.SignInWithEmailPresenter;
import com.overstock.android.prototype.view.SignInWithEmailView;

/**
 * Created by gcarroll on 20/04/2016.
 */
public class SignInWithEmailPresenterImpl implements SignInWithEmailPresenter {

  private static final String TAG = SignInWithEmailPresenterImpl.class.getName();

  private SignInWithEmailView signInWithEmailView;

  private Context context;

  public SignInWithEmailPresenterImpl(Context context) {
    this.context = context;
  }

  @Override
  public void setView(SignInWithEmailView signInWithEmailView) {
    this.signInWithEmailView = signInWithEmailView;
  }

  @Override
  public void onSignIn(String username, String password) {
    Log.v(TAG, "Signing In with email...");
    Toast.makeText(context, "Sign In to be implemented; Username: " + username + " Password: " + password,
      Toast.LENGTH_SHORT).show();
    final Intent signInIntent = new Intent(context, CommunityActivity.class);
    signInIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(signInIntent);
  }

  @Override
  public void onSignInError(String message) {
    Toast.makeText(context, "Sign In Error: " + message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void onDestroy() {
    this.signInWithEmailView = null;
  }
}
